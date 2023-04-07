package com.cydeo.day4;

import com.cydeo.utilities.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

        assertEquals(200, response.statusCode());

        //print the limit result
        System.out.println(response.path("limit").toString());

        //print the limit hasmore
        System.out.println(response.path("hasMore").toString());

        //print first countryID
        System.out.println(response.path("items[0].country_id").toString());
        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        //print second country name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //print "http://52.207.61.129:1000/ords/hr/countries/CA" print that
        String result = response.path("items[2].links[0].href");
        System.out.println("result = " + result);

        //get me all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //list of links
        List<String> links = response.path("items.links.href");
        System.out.println("links = " + links);

        //assert that all region ids equals to 2
        List<Integer> regionIds = response.path("items.region_id");

        for (Integer each : regionIds) {
            assertEquals(2, each);
        }


    }


    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .log().all()
                .when()
                .get("/employees");

        List<String> allJobIds = response.path("items.job_id");

        for (String each : allJobIds) {
            assertEquals("IT_PROG", each);
        }


    }


    //HW
    //print name of each IT_PROGs

}

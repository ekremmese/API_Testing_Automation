package com.cydeo.day4;

import com.cydeo.utilities.*;
import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiWithJsonPath extends HRTestBase {


    @DisplayName("GET request countries")
    @Test
    public void test1(){

        Response response = get("/countries");

        //to use jsonpath we assign response to jsonpath
        JsonPath jsonPath = response.jsonPath();

        //get the second country name with JsonPath
        System.out.println("jsonPath.get(\"items[1].country_name\") = " + jsonPath.get("items[1].country_name"));

        //get all country id
        //   "items.region_id"
        List<String> allCountryIDs = jsonPath.getList("items.country_id");

        System.out.println("allCountryIDs = " + allCountryIDs);

        //get all country names where their region id is equal to 2
        List<String> countriesWithRegionID2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");


    }


    @DisplayName("GET request /employees with query param")
    @Test
    public void test2(){

        Response response = given().queryParam("limit",107)
                .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        //get me all email of employees who is working as IT_PROG
        List<String> emailOfITProgrammers = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");

        System.out.println("emailOfITProgrammers = " + emailOfITProgrammers);

        //get me first name of employees who is making more than 10000

        List<Object> list = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("list = " + list);

        //get the max salary firstName
        String maxSalaryName = jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("maxSalaryName = " + maxSalaryName);

       String nameWithPath = response.path("items.max {it.salary}.first_name");
        System.out.println("nameWithPath = " + nameWithPath);


    }

}

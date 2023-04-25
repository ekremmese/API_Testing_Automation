package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import com.cydeo.utilities.*;
import io.restassured.http.*;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JSONtoJAVATest extends SpartanTestBase {

    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap() {

        Response response = given().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .extract().response();

        Map<String, Object> jsonMap = response.as(Map.class);

        System.out.println(jsonMap.toString());

        Integer actualID = (Integer) jsonMap.get("id");

        MatcherAssert.assertThat(actualID, is(15));



    }

    @DisplayName("GET all spartans to Java data structure")
    @Test
    public void test2(){

        Response response = given().get("/api/spartans/")
                .then()
                .statusCode(200)
                .extract().response();

        List <Map <String, Object>> allSpartans = response.as(List.class);

        System.out.println(allSpartans.get(0).get("name"));

        int counter = 1;
        for (Map<String, Object> allSpartan : allSpartans) {
            System.out.println(counter + " - " + allSpartan);
            counter++;
        }

        //System.out.println(allSpartans);


    }


}


//2:57:23
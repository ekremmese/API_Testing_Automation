package com.cydeo.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithParameters {


    @BeforeAll
    public static void init() {

        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.87.214.234:8000";

    }

    @Test
    public void test1() {

        /*   Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload
       */

        Response response = given().contentType(ContentType.JSON).and().pathParam("id", 5).when().get("/api/spartans/{id}");

        //Then response status code should be 200
        assertEquals(200, response.statusCode());

        //And response content-type: application/json
        assertEquals("application/json", response.contentType());

        //And "Blythe" should be in response payload
        assertTrue(response.body().asString().contains("Blythe"));


    }


     /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON).and().pathParam("id", 500).get("/api/spartans/{id}");

        //Then response status code should be 404
        assertEquals(404, response.statusCode());

        // And response content-type: application/json
        assertEquals("application/json", response.contentType());

        //And "Not Found" message should be in response payload
        assertTrue(response.body().asString().contains("Not Found"));


    }

      /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @Test
    public void test3(){
        Response response = given().log().all()
                .accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "e")
                .when()
                .get("/api/spartans/search");

        //Then response status code should be 200
        assertEquals(200,response.statusCode());

        //        And response content-type: application/json
        assertEquals("application/json", response.contentType());

        //        And "Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));

        //        And "Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));


    }

    /*
       Given accept type is Json
       And query parameter values are:
       gender|Female
       nameContains|e
       When user sends GET request to /api/spartans/search
       Then response status code should be 200
       And response content-type: application/json
       And "Female" should be in response payload
       And "Janette" should be in response payload
    */

    @DisplayName("GET request to /api/spartans/search with Query Params (MAP)")
    @Test
    public void test4(){

        //create a Map and add query parameters
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("nameContaints", "e");
        queryMap.put("gender", "Female");

        Response response = given()
                .log().all()
                .accept(ContentType.JSON)
                .and()
                .queryParams(queryMap)
                .when()
                .get("/api/spartans/search");


        //Then response status code should be 200
        assertEquals(200,response.statusCode());

        //        And response content-type: application/json
        assertEquals("application/json", response.contentType());

        //        And "Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));

        //        And "Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));

    }

}

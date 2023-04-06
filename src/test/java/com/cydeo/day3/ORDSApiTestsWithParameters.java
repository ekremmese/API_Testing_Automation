package com.cydeo.day3;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestsWithParameters {

    @BeforeAll
    public static void init() {

        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.87.214.234:1000/ords/hr/";

    }

     /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"region_id\":2}")
                .log().all()
                .when()
                .get("/countries");

        //Then status code is 200
        assertEquals(200, response.statusCode());
        //And Content type is application/json
        assertEquals("application/json", response.header("Content-Type"));
        //And Payload should contain "United States of America"
        assertTrue(response.body().asString().contains("United States of America"));

        response.prettyPrint();


    }

    /*
        Send a GET request to employees and get only employees who works as a IT_PROG

     */

    @Test
    public void test2(){

        Response response = given().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .log().all()
                .when()
                .get("/employees");




    }


}

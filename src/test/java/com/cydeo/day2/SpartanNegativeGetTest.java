package com.cydeo.day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanNegativeGetTest {

     /*TASK
    Given Accept type application/xml
    When user send GET request to /api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
    */

    @BeforeAll
    public static void init() {

        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.87.214.234:8000";

    }

    @DisplayName("Spartans Negative")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.XML)
                .when()
                .get("/api/spartans/10");

        //Then status code must be 406
        assertEquals(406, response.statusCode() );

        //And response Content Type must be application/xml;charset=UTF-8
        assertEquals("application/xml;charset=UTF-8", response.contentType());


    }


}

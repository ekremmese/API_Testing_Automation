package com.cydeo.day2;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class SpartanGetRequests {

    String baseUrl = "http://54.87.214.234:8000";

    //    Given Accept type application/json
    //    When user send GET request to api/spartans end point
    //    Then status code must 200
    //    And response Content Type must be application/json
    //    And response body should include spartan result

    @Test
    public void test1() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans");

        //printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        //print response content type
        System.out.println("response.contentType() = " + response.contentType());

        //print whole result body
        response.prettyPrint();

        //how to do API testing

        //verify status code is 200
        Assertions.assertEquals(response.statusCode(), 200);

        //verify content type is applictaion
        Assertions.assertEquals(response.contentType(), "application/json");
    }


    /*
        Given accept header is application/json
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */

    @DisplayName("Get one spartan/api/spaartans/3 and verify")
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans/3");

        //verify status code is 200 or not
        Assertions.assertEquals(200,response.statusCode());

        //verify content type is json
        Assertions.assertEquals("application/json",response.contentType());

        //verify response body contains 'Fidole'
        Assertions.assertTrue(response.body().asString().contains("Fidole"));

    }

    /*
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */

    @Test
    public void test3(){

        Response response = RestAssured
                .when().get(baseUrl + "/api/hello");

        //verify status code is 200 or not
        Assertions.assertEquals(200,response.statusCode());

        //verify content type is json
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());

        //verify we have headers named date
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        //content length should be 17
        Assertions.assertEquals("17", response.header("Content-Length"));

        // response body should be “Hello from Sparta"
        Assertions.assertTrue(response.body().asString().equals("Hello from Sparta"));


    }


}

package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://54.87.214.234:8000/api/spartans";

    @Test
    public void test1() {

        //send a get request and save response inside the Response object
        Response response = RestAssured.get(url);

        //print response status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //print response body in a pretty format
        response.prettyPrint();

    }

}

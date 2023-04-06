package com.cydeo.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithPath {

    @BeforeAll
    public static void init() {

        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.87.214.234:8000";

    }

    /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", "10")
                .when()
                .get("api/spartans/{id}");

        //Then status code is 200
        assertEquals(200,response.statusCode());

        //     And content-type is "application/json"
        assertEquals("application/json", response.header("Content-Type"));

        /*
        And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
         */

        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        assertEquals(10,id);
        assertEquals("Lorenza", name);
        assertEquals("Female",gender);
        //assertEquals(3312820936, phone);  // I can not assert it because it says number


    }

    @DisplayName("GET all spartan and navigate with Path()")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans");

        //response.prettyPrint();

        int firstId = response.path("id[0]");
        System.out.println(firstId);

        String name = response.path("name[0]");
        System.out.println(name);

        String lastFirstName = response.path("name[-1]");
        System.out.println("lastFirstName = " + lastFirstName);


        //save names inside the list of string
        List<String> names = response.path("name");
        System.out.println(names);
        //print each name one by one
        for (String n : names) {
            System.out.println(n);
        }


    }




}





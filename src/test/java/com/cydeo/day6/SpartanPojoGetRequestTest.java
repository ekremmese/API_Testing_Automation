package com.cydeo.day6;

import com.cydeo.pojos.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.*;

public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName("GET one spartan and convert it to Spartan object")
    @Test
    public void oneSpartanPojo(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("api/spartans/{id}")
                .then().statusCode(200)
                .log().all()
                .extract().response();

        //De serialize --> JSON to POJO

        //2 different ways to do this
        //1st way:  using as() method

        Spartan spartan15 = response.as(Spartan.class);

        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());


    }

}

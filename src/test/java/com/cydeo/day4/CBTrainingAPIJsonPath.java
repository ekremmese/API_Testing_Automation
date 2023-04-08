package com.cydeo.day4;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class CBTrainingAPIJsonPath {

    @BeforeAll
    public static void init() {
        //saved baseUrl inside this variable so that we don t need to type
        RestAssured.baseURI = "http://api.cybertektraining.com";
    }

}

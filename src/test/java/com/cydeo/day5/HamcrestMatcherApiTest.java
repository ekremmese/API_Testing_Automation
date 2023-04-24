package com.cydeo.day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatcherApiTest {

    @DisplayName("OnePartan with Hamcrest and chaining")
    @Test
    public void test1() {


        /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
         {
    "id": 15,
    "name": "Meta",
    "gender": "Female",
    "phone": 1938695106
            }
   */

        //this is called chaining

        given().log().all()
                .accept(ContentType.JSON)
                .and()
                    .pathParam("id", 15)
                .when()
                    .get("http://54.87.214.234:8000/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .body("id",equalTo(15),
                        "name",is("Meta"),
                        "gender", is("Female"),
                        "phone", is(1938695106))
                .log().all();


    }

    @DisplayName("GET request to teacher/all and chaining")
    @Test
    public void teachersTest(){

        given()
                    .accept(ContentType.JSON)
                .and()
                    .pathParam("id", 5)
                .when()
                    .get("https://api.training.cydeo.com/teacher/{id}")
                .then()
                    .statusCode(200)
                .and()
                    .contentType("application/json;charset=UTF-8")
                .and()
                    .header("Date", notNullValue())
                .and()
                    .body("teachers[0].firstName", is("Mario"),
                        "teachers[0].lastName", is("Luigi"),
                        "teachers[0].gender", is("Male"));



    }

    @DisplayName("GET request to teacher/all and chaining")
    @Test
    public void test3(){

        //verify "Valter","Mario","Porter")

        given()
                .accept(ContentType.JSON)
                .when()
                .get("https://api.training.cydeo.com/teacher/all")
                .then()
                .statusCode(200)
                .and()
                .body("teachers.firstName", hasItems("Valter","Mario","Porter"));

    }


}

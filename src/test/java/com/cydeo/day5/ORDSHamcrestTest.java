package com.cydeo.day5;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSHamcrestTest extends HRTestBase {

    @DisplayName("GET request to Regions endpoint and chaining")
    @Test
    public void test1() {

        //send a get request to employees endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are "Alexander", "Bruce", "David", "Valli" ,"Diana"...(find proper method to check list against list)
        //verify emails without checking order (provide emails in different order, just make sure it has same emails)

        given().contentType(ContentType.JSON)
                .and()
                .queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .body("items.first_name", containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana")); //we can use hasItems for the last body assertion


    }

    @Test
    public void test2() {

        //we want to chain and also get response object

        JsonPath jsonPath = given().contentType(ContentType.JSON)
                .and()
                .queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().jsonPath();

        //extract()--> method that allows us the get response object after we use then
        //assert that we have only 5 firstnames

        assertThat(jsonPath.getList("items.first_name"), hasSize(5));

        //assert firstNames order
        assertThat(jsonPath.getList("items.first_name"), containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"));


    }


}

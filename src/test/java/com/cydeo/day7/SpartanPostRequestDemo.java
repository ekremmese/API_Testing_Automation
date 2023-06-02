package com.cydeo.day7;

import com.cydeo.day5.HamcrestMatcherApiTest;
import com.cydeo.pojos.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

 /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Yahya",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */

public class SpartanPostRequestDemo extends SpartanTestBase {

    @Test
    public void postMethod1() {

        String requestJsonBody = "{\"gender\":\"Male\",\"name\":\"Yahya\",\"phone\":5053056732}";

        Response response = given().accept(ContentType.JSON).and()  //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(requestJsonBody)
                .when()
                .post("api/spartans");

        //verify status code
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedMessage));

        assertThat(response.path("data.name"), is("Yahya"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(5053056732L));


    }

    @DisplayName("POST with Map to JSON")
    @Test
    public void postMethod2() {

        //create a map to keep a request json body
        Map<String, Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("name", "Murat");
        requestJsonMap.put("gender", "Male");
        requestJsonMap.put("phone", 5412345690L);


        String requestJsonBody = "{\"gender\":\"Male\",\"name\":\"Yahya\",\"phone\":5053056732}";

        Response response = given().accept(ContentType.JSON).and()  //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(requestJsonMap).log().all()
                .when()
                .post("api/spartans");

        //verify status code
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedMessage));

        assertThat(response.path("data.name"), is("Murat"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(5412345690L));

        response.prettyPrint();


    }

    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod3() {


        Spartan spartan = new Spartan();
        spartan.setName("Murat");
        spartan.setGender("Male");
        spartan.setPhone(5412345690L);

        System.out.println("spartan = " + spartan);


        String requestJsonBody = "{\"gender\":\"Male\",\"name\":\"Yahya\",\"phone\":5053056732}";

        Response response = given().accept(ContentType.JSON).and()  //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(spartan).log().all()
                .when()
                .post("api/spartans");

        //verify status code
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedMessage));

        assertThat(response.path("data.name"), is("Murat"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(5412345690L));

        response.prettyPrint();


    }

    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod4() {

//this example we implement serialization with creatin spartan object sending as a request body
        //also implemented deserialization getting the id,sending get request and saving that body as a response

        //create one object from your pojo, send it as a JSON
        Spartan spartan = new Spartan();
        spartan.setName("BruceWayne");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        System.out.println("spartan = " + spartan);
        String expectedResponseMessage = "A Spartan is Born!";

        int idFromPost = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(spartan).log().all()
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is(expectedResponseMessage))
                .extract().jsonPath().getInt("data.id");

        System.out.println("idFromPost = " + idFromPost);
        //send a get request to id
        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParam("id", idFromPost)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).log().all().extract().as(Spartan.class);

        assertThat(spartanPosted.getName(),is(spartan.getName()));
        assertThat(spartanPosted.getGender(),is(spartan.getGender()));
        assertThat(spartanPosted.getPhone(),is(spartan.getPhone()));
        assertThat(spartanPosted.getId(),is(idFromPost));

    }


}

package com.cydeo.day6;

import com.cydeo.pojos.Employee;
import com.cydeo.pojos.Region;
import com.cydeo.pojos.Regions;
import com.cydeo.utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.MatcherAssert.*;
import static  org.hamcrest.Matchers.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void regionTest() {

        JsonPath jsonPath = RestAssured.get("/regions").then().statusCode(200).extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println("region1 = " + region1);

        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());

        System.out.println("region1.getLinks().get(1).getHref() = " + region1.getLinks().get(0).getHref());


    }

    @DisplayName("GET request to /employees and only get couple of values as a Pojo class ")
    @Test
    public void employeeGet() {

        Employee employee1 = RestAssured.get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);

        System.out.println("employee1 = " + employee1);


    }

    /*
    send a get request to regions
    verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4 done
        try to use pojo as much as possible
        ignore non used fields

     */

    @Test
    public void regionPojoTest(){

        //
        Regions regions = RestAssured.get("/regions").then().statusCode(200)
                .extract().response().as(Regions.class);

        assertThat(regions.getCount(),is(4));

        List<Integer> regionIds = new ArrayList<>();
        List<String> regionNames = new ArrayList<>();

        List<Region> items = regions.getItems();

        for (Region region : items) {

            regionIds.add(region.getRegionId());
            regionNames.add(region.getRegion_name());

        }

        System.out.println("regionIds = " + regionIds);
        System.out.println("regionNames = " + regionNames);

        //expected result
        List<Integer> expectedRegionIds = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe" ,"Americas" , "Asia", "Middle East and Africa");

        //assertion
        assertThat(regionIds, is(expectedRegionIds));
        assertThat(regionNames,is(expectedRegionNames));





    }


}


//2:15:33
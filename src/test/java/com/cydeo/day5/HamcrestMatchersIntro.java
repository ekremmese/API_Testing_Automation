package com.cydeo.day5;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HamcrestMatchersIntro {

    @Test
    public void test1() {

        //Hamcrest is an assertion library. Asserting with plain english.

        //actual 5+5
        assertThat(5 + 5, is(2 + 8));   // --> plain english

        assertThat(5 + 5, equalTo(10));

        //this is
        assertThat(4 + 4, is(equalTo(8)));

        assertThat(4 + 6, not(11));
        assertThat(5 + 5, is(not(11)));
        assertThat(5 + 5, is(not(equalTo(11))));

        //number comparison

        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()


    }

    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest() {

        String text = "B22 is learning Hamcrest";

        //checking for equality is same as numbers
        assertThat(text, is("B22 is learning Hamcrest"));
        assertThat(text, equalTo("B22 is learning Hamcrest"));
        assertThat(text, is(equalTo("B22 is learning Hamcrest")));

        //check if this text starts with B22
        assertThat(text, startsWith("B22"));

        //case insensitive
        assertThat(text, startsWithIgnoringCase("b22"));

        //ends with
        assertThat(text, endsWith("rest"));

        //check if text contains String learning
        assertThat(text, containsString("learning"));

        //contains insensitive
        assertThat(text, containsStringIgnoringCase("LEARNING"));

        String str = " ";
        assertThat(str,blankString());
        assertThat(str,is(blankString()));

        //check if trimmed str is empty string
        assertThat(str.trim(), emptyString());



    }

    @DisplayName("Hamcrest for Collection")
    @Test
    public void testCollection(){

        List<Integer> listOfNumbers= Arrays.asList(1,4,5,6,32,54,66,77,45,23);

        //check size of the list
        assertThat(listOfNumbers, hasSize(10));  // ---> we don't need to even say listOfNumber.size();

        //check if this list hasItem 77
        assertThat(listOfNumbers,hasItem(77));

        //check if this list has items 77,54 and 23
        assertThat(listOfNumbers, hasItems(77,54,23));

        assertThat(listOfNumbers, is(hasItem(23)));  // ---> that also works with is(hasItem())

        //check if all numbers greater than
        assertThat(listOfNumbers, everyItem(greaterThan(5)));  //--> magical method, no need loops for


    }

}

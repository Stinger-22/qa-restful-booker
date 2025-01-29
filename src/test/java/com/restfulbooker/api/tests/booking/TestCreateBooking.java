package com.restfulbooker.api.tests.booking;

import com.restfulbooker.api.requests.BookingAPI;
import com.restfulbooker.api.requests.entities.BookingDates;
import com.restfulbooker.api.requests.entities.BookingRequest;
import com.restfulbooker.api.requests.entities.BookingResponse;
import com.restfulbooker.api.requests.util.Utilities;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class TestCreateBooking extends BookingAPI {
    private static ResponseSpecification invalidBooking;

    @BeforeAll
    static void buildExpectedResponses() {
        invalidBooking = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    @AfterAll
    static void teardown() {
        Utilities.deleteAllBookings();
    }

    @ParameterizedTest(name = "{0}")
    @CsvFileSource(resources = "/validBookings.csv", numLinesToSkip = 1, delimiter = '|')
    void createBooking(String testcasename,
                       String firstname,
                       String lastname,
                       Integer totalprice,
                       Boolean depositpaid,
                       String checkin,
                       String checkout,
                       String additionalneeds) {
        BookingRequest br = new BookingRequest(firstname, lastname, totalprice, depositpaid, new BookingDates(checkin, checkout), additionalneeds);

        Response response = given(createBooking(br, ContentType.JSON.toString(), null))
        .when()
            .post(PATH);
        response.then()
            .statusCode(201);

        BookingResponse receivedBody = response.body().as(BookingResponse.class);
        assert receivedBody.booking().equals(br);
    }

    @ParameterizedTest(name = "{0}")
    @CsvFileSource(resources = "/invalidBookings.csv", numLinesToSkip = 1, delimiter = '|')
    void createInvalidBooking(String testcasename,
                              String firstname,
                              String lastname,
                              Integer totalprice,
                              Boolean depositpaid,
                              String checkin,
                              String checkout,
                              String additionalneeds) {
        BookingRequest br = new BookingRequest(firstname, lastname, totalprice, depositpaid, new BookingDates(checkin, checkout), additionalneeds);


        given(createBooking(br, ContentType.JSON.toString(), null))
        .when()
            .post(PATH)
        .then()
            .spec(invalidBooking);
    }
}

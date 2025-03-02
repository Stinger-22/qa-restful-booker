package com.restfulbooker.api.tests.booking;

import com.fasterxml.jackson.databind.JsonNode;
import com.restfulbooker.api.TestBaseAPI;
import com.restfulbooker.api.requests.BookingAPI;
import com.restfulbooker.api.requests.entities.BookingDates;
import com.restfulbooker.api.requests.entities.BookingRequest;
import com.restfulbooker.api.requests.entities.BookingResponse;
import com.restfulbooker.api.requests.util.Utilities;
import com.restfulbooker.api.util.BookingTestCase;
import com.restfulbooker.api.util.JSONDataReader;
import com.restfulbooker.api.util.ObjectArray;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.ITest;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import static io.restassured.RestAssured.given;

public class TestCreateBooking extends TestBaseAPI implements ITest {
    private final ThreadLocal<String> testName = new ThreadLocal<>();

    private static ResponseSpecification invalidBooking;

    private final JSONDataReader reader = new JSONDataReader() {
        @Override
        protected ObjectArray[] parse(InputStream is) throws IOException {
            JsonNode json = MAPPER.readTree(is);
            return MAPPER.treeToValue(json, BookingTestCase[].class);
        }
    };

    @DataProvider(name = "validBookings")
    Object[][] validBookings() {
        return reader.read("booking/validBookings.json");
    }

    @DataProvider(name = "invalidBookings")
    Object[][] invalidBookings() {
        return reader.read("booking/invalidBookings.json");
    }

    @BeforeMethod
    void setTestName(Method method, Object[] testData) {
        testName.set(method.getName() + "_" + testData[0]);
    }

    @Override
    public String getTestName() {
        return testName.get();
    }

    @BeforeTest
    static void buildExpectedResponses() {
        invalidBooking = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    @AfterTest
    static void teardown() {
        Utilities.deleteAllBookings();
    }

    @Test(dataProvider = "validBookings")
    @Parameters
    void createBooking(String description,
                       String firstname,
                       String lastname,
                       Integer totalprice,
                       Boolean depositpaid,
                       BookingDates bookingDates,
                       String additionalneeds) {
        BookingRequest br = new BookingRequest(firstname, lastname, totalprice, depositpaid, bookingDates, additionalneeds);

        Response response = given(BookingAPI.createBooking(br, ContentType.JSON.toString(), null))
        .when()
            .post(BookingAPI.PATH);
        response.then()
            .statusCode(201);

        BookingResponse receivedBody = response.body().as(BookingResponse.class);
        assert receivedBody.booking().equals(br);
    }

    @Test(dataProvider = "invalidBookings")
    @Parameters
    void createInvalidBooking(String description,
                              String firstname,
                              String lastname,
                              Integer totalprice,
                              Boolean depositpaid,
                              BookingDates bookingDates,
                              String additionalneeds) {
        BookingRequest br = new BookingRequest(firstname, lastname, totalprice, depositpaid, bookingDates, additionalneeds);

        given(BookingAPI.createBooking(br, ContentType.JSON.toString(), null))
        .when()
            .post(BookingAPI.PATH)
        .then()
            .spec(invalidBooking);
    }
}

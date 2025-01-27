package com.restfulbooker.api.requests;

import com.restfulbooker.api.BaseAPI;
import com.restfulbooker.api.requests.entities.BookingRequest;
import com.restfulbooker.api.requests.util.BookingSearch;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class BookingAPI extends BaseAPI {
    public static final String PATH = "/booking";

    public static RequestSpecification getBookingIds(BookingSearch by) {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        if (by.firstname() != null) {
            builder.addQueryParam("firstname", by.firstname());
        }
        if (by.lastname() != null) {
            builder.addQueryParam("lastname", by.lastname());
        }
        if (by.checkin() != null) {
            builder.addQueryParam("checkin", by.checkin());
        }
        if (by.checkout() != null) {
            builder.addQueryParam("checkout", by.checkout());
        }

        return builder.build();
    }

    public static RequestSpecification getBooking(int id, String responseType) {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.addPathParam("id", id);
        setResponseType(builder, responseType);

        return builder.build();
    }

    public static RequestSpecification createBooking(BookingRequest body, String requestType, String responseType) {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        setRequestType(builder, requestType);
        setResponseType(builder, responseType);
        setBody(builder, body);

        return builder.build();
    }

    public static RequestSpecification updateBooking(int id, BookingRequest body, String requestType, String responseType) {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.addPathParam("id", id);
        setRequestType(builder, requestType);
        setResponseType(builder, responseType);
        setBody(builder, body);

        return builder.build();
    }

    public static RequestSpecification patchBooking(int id, Map<String, String> body, String requestType, String responseType) {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.addPathParam("id", id);
        setRequestType(builder, requestType);
        setResponseType(builder, responseType);
        setBody(builder, body);

        return builder.build();
    }

    public static RequestSpecification deleteBooking(int id) {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.addPathParam("id", id);

        return builder.build();
    }

    private static void setBody(RequestSpecBuilder builder, Object body) {
        if (body != null) {
            builder.setBody(body);
        }
    }

    private static void setRequestType(RequestSpecBuilder builder, String requestType) {
        if (requestType != null) {
            builder.setContentType(requestType);
        }
    }

    private static void setResponseType(RequestSpecBuilder builder, String responseType) {
        if (responseType != null) {
            builder.setAccept(responseType);
        }
    }
}

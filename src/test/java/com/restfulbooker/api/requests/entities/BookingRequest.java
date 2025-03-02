package com.restfulbooker.api.requests.entities;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public record BookingRequest(String firstname,
                             String lastname,
                             Integer totalprice,
                             Boolean depositpaid,
                             BookingDates bookingdates,
                             String additionalneeds) {

    public static BookingRequest defaultBooking() {
        ObjectMapper mapper = new ObjectMapper();
        BookingRequest defaultBooking;
        try (InputStream is = BeanProperty.class.getClassLoader().getResourceAsStream("booking/defaultBooking.json")) {
            defaultBooking = mapper.readValue(is, BookingRequest.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return defaultBooking;
    }

    public static List<BookingRequest> defaultBookings() {
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        List<BookingRequest> defaultBookings;
        try (InputStream is = BeanProperty.class.getClassLoader().getResourceAsStream("booking/defaultBookings.json")) {
            defaultBookings = mapper.readValue(is, typeFactory.constructCollectionType(List.class, BookingRequest.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return defaultBookings;
    }
}

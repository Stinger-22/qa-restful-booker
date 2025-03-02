package com.restfulbooker.api.util;

import com.restfulbooker.api.requests.entities.BookingDates;

public record BookingTestCase(String description,
                              String firstname,
                              String lastname,
                              Integer totalprice,
                              Boolean depositpaid,
                              BookingDates bookingdates,
                              String additionalneeds) implements ObjectArray {

    public Object[] toArray() {
        return new Object[]{description, firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds};
    }
}

package com.restfulbooker.api.requests.entities;

public record BookingRequest(String firstname,
                             String lastname,
                             int totalprice,
                             boolean depositpaid,
                             BookingDates bookingdates,
                             String additionalneeds) {

}

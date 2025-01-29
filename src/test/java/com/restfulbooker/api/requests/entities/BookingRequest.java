package com.restfulbooker.api.requests.entities;

public record BookingRequest(String firstname,
                             String lastname,
                             Integer totalprice,
                             Boolean depositpaid,
                             BookingDates bookingdates,
                             String additionalneeds) {

    public static BookingRequest defaultEntity() {
        return new BookingRequest("Sam", "Gamgee", 140, true,
                new BookingDates(
                        "2025-02-14",
                       "2025-03-01"
                ), "Breakfast. Second breakfast");
    }
}

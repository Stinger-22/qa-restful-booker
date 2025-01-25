package com.restfulbooker.api.requests.util;

import java.time.LocalDate;

public record BookingSearch(String firstname,
                            String lastname,
                            LocalDate checkin,
                            LocalDate checkout) {

    public static final class Builder {
        private String firstname;
        private String lastname;
        private LocalDate checkin;
        private LocalDate checkout;

        public Builder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder checkin(LocalDate checkin) {
            this.checkin = checkin;
            return this;
        }

        public Builder checkout(LocalDate checkout) {
            this.checkout = checkout;
            return this;
        }

        public BookingSearch build() {
            return new BookingSearch(firstname, lastname, checkin, checkout);
        }
    }
}

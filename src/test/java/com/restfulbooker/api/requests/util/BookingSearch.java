package com.restfulbooker.api.requests.util;

public record BookingSearch(String firstName,
                            String lastName,
                            String checkin,
                            String checkout) {

    public static final class Builder {
        private String firstName;
        private String lastName;
        private String checkin;
        private String checkout;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder checkin(String checkin) {
            this.checkin = checkin;
            return this;
        }

        public Builder checkout(String checkout) {
            this.checkout = checkout;
            return this;
        }

        public BookingSearch build() {
            return new BookingSearch(firstName, lastName, checkin, checkout);
        }
    }
}

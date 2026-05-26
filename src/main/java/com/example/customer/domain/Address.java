package com.example.customer.domain;

import java.util.Objects;

public final class Address {
    private final String street;
    private final String number;
    private final String complement;
    private final String neighborhood;
    private final String city;
    private final String state;

    public Address(
            final String street,
            final String number,
            final String complement,
            final String neighborhood,
            final String city,
            final String state
    ) {
        this.street = requireText(street, "street");
        this.number = requireText(number, "number");
        this.complement = complement == null ? null : complement.trim();
        this.neighborhood = requireText(neighborhood, "neighborhood");
        this.city = requireText(city, "city");
        this.state = requireText(state, "state");
    }

    public String street() {
        return street;
    }

    public String number() {
        return number;
    }

    public String complement() {
        return complement;
    }

    public String neighborhood() {
        return neighborhood;
    }

    public String city() {
        return city;
    }

    public String state() {
        return state;
    }

    private static String requireText(final String value, final String fieldName) {
        Objects.requireNonNull(value, fieldName + " is required");
        final String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        return trimmed;
    }
}

package com.example.customer.domain;

import java.time.LocalDate;

public final class CustomerFactory {
    private CustomerFactory() {
    }

    public static Customer create(
            final String name,
            final String customerIdentifier,
            final LocalDate dateOfBirth,
            final Document document,
            final Address address
    ) {
        return new Customer(name, customerIdentifier, dateOfBirth, document, address);
    }
}

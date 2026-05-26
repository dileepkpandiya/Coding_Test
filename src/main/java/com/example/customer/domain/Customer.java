package com.example.customer.domain;

import java.time.LocalDate;
import java.util.Objects;

public final class Customer {
    private static final int MAX_NAME_LENGTH = 60;

    private final String name;
    private final String customerIdentifier;
    private final LocalDate dateOfBirth;
    private final Document document;
    private final Address address;

    public Customer(
            final String name,
            final String customerIdentifier,
            final LocalDate dateOfBirth,
            final Document document,
            final Address address
    ) {
        final String trimmedName = requireText(name, "name");
        if (trimmedName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("name must be at most 60 characters");
        }

        this.name = trimmedName;
        this.customerIdentifier = requireText(customerIdentifier, "customerIdentifier");
        this.dateOfBirth = Objects.requireNonNull(dateOfBirth, "dateOfBirth is required");
        this.document = Objects.requireNonNull(document, "document is required");
        this.address = Objects.requireNonNull(address, "address is required");
    }

    public String name() {
        return name;
    }

    public String customerIdentifier() {
        return customerIdentifier;
    }

    public LocalDate dateOfBirth() {
        return dateOfBirth;
    }

    public Document document() {
        return document;
    }

    public Address address() {
        return address;
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

package com.example.customer.domain;

import java.util.Objects;

public final class Document {
    private final DocumentType type;
    private final String value;

    public Document(final DocumentType type, final String value) {
        this.type = Objects.requireNonNull(type, "type is required");
        this.value = onlyDigits(value);

        if (!isValid(this.type, this.value)) {
            throw new IllegalArgumentException(this.type + " is invalid");
        }
    }

    public DocumentType type() {
        return type;
    }

    public String value() {
        return value;
    }

    public static Document cpf(final String value) {
        return new Document(DocumentType.CPF, value);
    }

    public static Document cnpj(final String value) {
        return new Document(DocumentType.CNPJ, value);
    }

    private static boolean isValid(final DocumentType type, final String value) {
        return switch (type) {
            case CPF -> isValidCpf(value);
            case CNPJ -> isValidCnpj(value);
        };
    }

    private static boolean isValidCpf(final String value) {
        if (value.length() != 11 || hasAllEqualDigits(value)) {
            return false;
        }

        final int firstDigit = calculateCpfDigit(value, 9);
        final int secondDigit = calculateCpfDigit(value, 10);
        return digitAt(value, 9) == firstDigit && digitAt(value, 10) == secondDigit;
    }

    private static int calculateCpfDigit(final String value, final int length) {
        int sum = 0;
        for (int index = 0; index < length; index++) {
            sum += digitAt(value, index) * (length + 1 - index);
        }
        final int remainder = (sum * 10) % 11;
        return remainder == 10 ? 0 : remainder;
    }

    private static boolean isValidCnpj(final String value) {
        if (value.length() != 14 || hasAllEqualDigits(value)) {
            return false;
        }

        final int firstDigit = calculateCnpjDigit(value, 12);
        final int secondDigit = calculateCnpjDigit(value, 13);
        return digitAt(value, 12) == firstDigit && digitAt(value, 13) == secondDigit;
    }

    private static int calculateCnpjDigit(final String value, final int length) {
        final int[] weights = length == 12
                ? new int[]{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}
                : new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int sum = 0;
        for (int index = 0; index < length; index++) {
            sum += digitAt(value, index) * weights[index];
        }

        final int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

    private static String onlyDigits(final String value) {
        Objects.requireNonNull(value, "value is required");
        return value.replaceAll("\\D", "");
    }

    private static int digitAt(final String value, final int index) {
        return Character.digit(value.charAt(index), 10);
    }

    private static boolean hasAllEqualDigits(final String value) {
        return value.chars().allMatch(character -> character == value.charAt(0));
    }
}

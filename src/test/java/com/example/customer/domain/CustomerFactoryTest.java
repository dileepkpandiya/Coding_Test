package com.example.customer.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class CustomerFactoryTest {
    @Test
    void createsCustomerWithCpfAndFullAddress() {
        final Address address = new Address(
                "Rua das Flores",
                "100",
                "Apto 12",
                "Centro",
                "Sao Paulo",
                "SP"
        );
        final Document document = Document.cpf("529.982.247-25");

        final Customer customer = CustomerFactory.create(
                "Maria Silva",
                "CUST-001",
                LocalDate.of(1990, 5, 10),
                document,
                address
        );

        assertEquals("Maria Silva", customer.name());
        assertEquals("CUST-001", customer.customerIdentifier());
        assertEquals(LocalDate.of(1990, 5, 10), customer.dateOfBirth());
        assertEquals(document, customer.document());
        assertEquals(address, customer.address());
    }

    @Test
    void createsCustomerWithCnpj() {
        final Customer customer = CustomerFactory.create(
                "Empresa Exemplo Ltda",
                "CUST-002",
                LocalDate.of(2000, 1, 1),
                Document.cnpj("11.222.333/0001-81"),
                validAddress()
        );

        assertEquals(DocumentType.CNPJ, customer.document().type());
        assertEquals("11222333000181", customer.document().value());
    }

    @Test
    void rejectsNameLongerThanSixtyCharacters() {
        final String name = "A".repeat(61);

        assertThrows(IllegalArgumentException.class, () -> CustomerFactory.create(
                name,
                "CUST-003",
                LocalDate.of(1990, 5, 10),
                Document.cpf("52998224725"),
                validAddress()
        ));
    }

    @Test
    void rejectsMissingRequiredCustomerFields() {
        assertThrows(NullPointerException.class, () -> CustomerFactory.create(
                null,
                "CUST-004",
                LocalDate.of(1990, 5, 10),
                Document.cpf("52998224725"),
                validAddress()
        ));
        assertThrows(IllegalArgumentException.class, () -> CustomerFactory.create(
                " ",
                "CUST-005",
                LocalDate.of(1990, 5, 10),
                Document.cpf("52998224725"),
                validAddress()
        ));
        assertThrows(NullPointerException.class, () -> CustomerFactory.create(
                "Maria Silva",
                "CUST-006",
                null,
                Document.cpf("52998224725"),
                validAddress()
        ));
        assertThrows(NullPointerException.class, () -> CustomerFactory.create(
                "Maria Silva",
                "CUST-007",
                LocalDate.of(1990, 5, 10),
                null,
                validAddress()
        ));
        assertThrows(NullPointerException.class, () -> CustomerFactory.create(
                "Maria Silva",
                "CUST-008",
                LocalDate.of(1990, 5, 10),
                Document.cpf("52998224725"),
                null
        ));
    }

    @Test
    void rejectsMissingRequiredAddressFields() {
        assertThrows(NullPointerException.class, () -> new Address(
                null,
                "100",
                null,
                "Centro",
                "Sao Paulo",
                "SP"
        ));
        assertThrows(IllegalArgumentException.class, () -> new Address(
                "Rua das Flores",
                " ",
                null,
                "Centro",
                "Sao Paulo",
                "SP"
        ));
    }

    private static Address validAddress() {
        return new Address(
                "Rua das Flores",
                "100",
                null,
                "Centro",
                "Sao Paulo",
                "SP"
        );
    }
}

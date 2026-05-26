package com.example.customer.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DocumentTest {
    @Test
    void acceptsValidCpf() {
        final Document document = Document.cpf("529.982.247-25");

        assertEquals(DocumentType.CPF, document.type());
        assertEquals("52998224725", document.value());
    }

    @Test
    void rejectsInvalidCpf() {
        assertThrows(IllegalArgumentException.class, () -> Document.cpf("529.982.247-26"));
        assertThrows(IllegalArgumentException.class, () -> Document.cpf("111.111.111-11"));
        assertThrows(IllegalArgumentException.class, () -> Document.cpf("123"));
    }

    @Test
    void acceptsValidCnpj() {
        final Document document = Document.cnpj("11.222.333/0001-81");

        assertEquals(DocumentType.CNPJ, document.type());
        assertEquals("11222333000181", document.value());
    }

    @Test
    void rejectsInvalidCnpj() {
        assertThrows(IllegalArgumentException.class, () -> Document.cnpj("11.222.333/0001-82"));
        assertThrows(IllegalArgumentException.class, () -> Document.cnpj("11.111.111/1111-11"));
        assertThrows(IllegalArgumentException.class, () -> Document.cnpj("123"));
    }

    @Test
    void rejectsMissingDocumentFields() {
        assertThrows(NullPointerException.class, () -> new Document(null, "52998224725"));
        assertThrows(NullPointerException.class, () -> Document.cpf(null));
    }
}

# Feature: Customer Domain Creation

## Requirements
- A customer must have a name, a customer identifier, date of birth, a document (either CPF or CNPJ), and a full address
- The name must be limited to 60 characters
- The address must include street, number, an optional complement field, neighborhood, city, and state
- The customer must be created using the factory pattern

## Business Rules
- CPF and CNPJ must follow Brazilian validation rules

## Acceptance Criteria
- Parameters must be final
- Unit tests must be created for customer creation and field validation
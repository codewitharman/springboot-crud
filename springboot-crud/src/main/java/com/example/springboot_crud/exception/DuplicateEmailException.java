package com.example.springboot_crud.exception;

public class DuplicateEmailException extends RuntimeException {

    private final String email;

    public DuplicateEmailException(String email) {
        super(String.format("An employee with email '%s' already exists", email));
        this.email = email;
    }

    public String getEmail() { return email; }
}

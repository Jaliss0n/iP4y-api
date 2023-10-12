package com.example.ip4y.exceptions;

public class CpfAlreadyExists extends RuntimeException {

    public CpfAlreadyExists() {
        super("CPF already exists in the database.");
    }

    public CpfAlreadyExists(String message) {
        super(message);
    }

    public CpfAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

}

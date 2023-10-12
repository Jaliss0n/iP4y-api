package com.example.ip4y.exceptions;

public class ClientNotExist extends RuntimeException {

    public ClientNotExist() {
        super("client not found, in this database.");
    }

    public ClientNotExist(String message) {
        super(message);
    }

    public ClientNotExist(String message, Throwable cause) {
        super(message, cause);
    }

}

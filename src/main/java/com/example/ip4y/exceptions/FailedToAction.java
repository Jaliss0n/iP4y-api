package com.example.ip4y.exceptions;

public class FailedToAction extends RuntimeException {

    public FailedToAction() {
        super("failure to act");
    }

    public FailedToAction(String message) {
        super(message);
    }

    public FailedToAction(String message, Throwable cause) {
        super(message, cause);
    }

}

package com.example.ip4y.controllers;
import com.example.ip4y.exceptions.ClientNotExist;
import com.example.ip4y.exceptions.CpfAlreadyExists;
import com.example.ip4y.exceptions.FailedToAction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CpfAlreadyExists.class)
    public ResponseEntity<String> handleCpfAlreadyExists(CpfAlreadyExists ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(ClientNotExist.class)
    public ResponseEntity<String> handleClientNotExist(ClientNotExist ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(FailedToAction.class)
    public ResponseEntity<String> handleFailedAction(FailedToAction ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
package com.example.employeemanagementsystem.exception;

public class EmployerNotFoundException extends RuntimeException{

    public EmployerNotFoundException() {
    }

    public EmployerNotFoundException(String message) {
        super(message);
    }

    public EmployerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.mshindelar.accountservice.exception;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() { super(); }

    public EmailNotFoundException(String message) { super(message); }
}

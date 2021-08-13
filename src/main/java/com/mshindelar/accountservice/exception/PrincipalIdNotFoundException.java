package com.mshindelar.accountservice.exception;

public class PrincipalIdNotFoundException extends RuntimeException {
    public PrincipalIdNotFoundException() { super(); }

    public PrincipalIdNotFoundException(String message) { super(message); }
}

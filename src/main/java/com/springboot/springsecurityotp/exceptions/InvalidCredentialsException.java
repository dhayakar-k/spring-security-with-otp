package com.springboot.springsecurityotp.exceptions;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException(String msg) {
        super(msg);
    }
}

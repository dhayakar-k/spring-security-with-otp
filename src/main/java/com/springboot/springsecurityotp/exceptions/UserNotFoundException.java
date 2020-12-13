package com.springboot.springsecurityotp.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String msg) {
        super(msg);
    }
}

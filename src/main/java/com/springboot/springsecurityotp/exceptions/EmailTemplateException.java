package com.springboot.springsecurityotp.exceptions;

public class EmailTemplateException  extends RuntimeException{

    public EmailTemplateException(String message) {
        super(message);
    }
}

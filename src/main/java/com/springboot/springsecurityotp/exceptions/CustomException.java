package com.springboot.springsecurityotp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CustomException extends RuntimeException{

    private int statusCode;
    private Date timeStamp;
    private String message;
}

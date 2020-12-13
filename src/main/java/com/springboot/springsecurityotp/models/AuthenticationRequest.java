package com.springboot.springsecurityotp.models;

import lombok.Data;


@Data
public class AuthenticationRequest {

    private String userName;

    private String password;
}

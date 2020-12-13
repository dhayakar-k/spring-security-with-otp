package com.springboot.springsecurityotp.controllers;

import com.springboot.springsecurityotp.models.AuthenticationRequest;

import com.springboot.springsecurityotp.services.OtpService;
import com.springboot.springsecurityotp.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
public class OtpController {

    @Autowired
    private UserService userService;

    @Autowired
    private  OtpService otpService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/generate-otp")
    public String generateOtp(@Valid @RequestBody AuthenticationRequest request) {

        /*try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),
                    request.getPassword()));
        } catch (Exception e){

            throw new InvalidCredentialsException("Invalid credentials");
        }*/

     //return otpService.generateOtp(request.getUserName());
     return userService.validateCredsAndGenerateOtp(request);
    }

}

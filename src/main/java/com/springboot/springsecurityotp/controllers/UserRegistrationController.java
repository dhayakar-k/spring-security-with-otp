package com.springboot.springsecurityotp.controllers;

import com.springboot.springsecurityotp.models.User;
import com.springboot.springsecurityotp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register-user")
public class UserRegistrationController {

    @Autowired
    private  UserService userService;

    @PostMapping
    public User registerNewUser(@Valid @RequestBody User user) {

        return userService.addNewUser(user);
    }

}

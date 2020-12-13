package com.springboot.springsecurityotp.models;

import com.springboot.springsecurityotp.models.enums.Role;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
@Document(collection = "user")
public class User {

    @Id
    private String id;

    private String userName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}

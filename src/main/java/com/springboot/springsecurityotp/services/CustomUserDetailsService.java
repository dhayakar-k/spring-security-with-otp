package com.springboot.springsecurityotp.services;

import com.springboot.springsecurityotp.exceptions.UserNotFoundException;
import com.springboot.springsecurityotp.models.User;
import com.springboot.springsecurityotp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user = userRepository.getByUserName(userName);
        if(user == null) {
           throw new UserNotFoundException("User Not Found with the given user name");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), grantedAuthorities);
    }
}

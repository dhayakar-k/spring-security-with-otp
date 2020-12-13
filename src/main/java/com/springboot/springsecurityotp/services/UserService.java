package com.springboot.springsecurityotp.services;

import com.springboot.springsecurityotp.EmailTemplate;
import com.springboot.springsecurityotp.exceptions.InvalidCredentialsException;
import com.springboot.springsecurityotp.models.AuthenticationRequest;
import com.springboot.springsecurityotp.models.User;
import com.springboot.springsecurityotp.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  OtpService otpService;

    @Autowired
    private EmailService emailService;

    @Value("${spring.mail.username}")
    private String emailAddress;

    @Value("${email.header}")
    private String emailHeader;

    public User addNewUser(User user) {
        log.info("Registering the new User {} ", user);
        return userRepository.save(user);

    }

    public String validateCredsAndGenerateOtp(AuthenticationRequest request) {

        User existingUser = userRepository.getByUserName(request.getUserName());
        if(existingUser.getUserName().equals(request.getUserName()) &&
                existingUser.getPassword().equals(request.getPassword())) {
            EmailTemplate emailTemplate = new EmailTemplate();
            Map<String, String> replacements = new HashMap<>();
            int otpNum = otpService.generateOtp(request.getUserName());
            replacements.put("user", existingUser.getUserName());
            replacements.put("otpNum", String.valueOf(otpNum));
            String msg = emailTemplate.getTemplate(replacements);
            emailService.sendOtp(emailAddress, emailHeader, msg);
            return "otp has been sent to your registered email address";
        } else {
            throw new InvalidCredentialsException("Invalid Credentials");
        }
    }
}

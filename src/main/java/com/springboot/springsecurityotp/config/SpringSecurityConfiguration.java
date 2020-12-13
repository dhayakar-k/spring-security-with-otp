package com.springboot.springsecurityotp.config;


import com.springboot.springsecurityotp.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

       @Autowired
       private CustomUserDetailsService userDetailsService;


       @Bean
       public AuthenticationProvider authenticationProvider() {

           DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
           daoAuthenticationProvider.setUserDetailsService(userDetailsService);
           daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
           return daoAuthenticationProvider;
       }

       @Bean
       public PasswordEncoder passwordEncoder() {
           return NoOpPasswordEncoder.getInstance();
       }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/api/register-user","/api/generate-otp")
                .permitAll().anyRequest().authenticated().and()
                .exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
}

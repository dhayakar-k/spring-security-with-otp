package com.springboot.springsecurityotp.models;

import com.springboot.springsecurityotp.models.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class MyUserDetails  implements UserDetails {

    @Autowired
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if(this.user.getRole().equals(Role.USER)) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(Role.USER.toString()));
        } else if(this.user.getRole().equals(Role.ADMIN)) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(Role.ADMIN.toString()));
        } else if(this.user.getRole().equals(Role.FLEET_COMPANY)){
            grantedAuthorityList.add(new SimpleGrantedAuthority(Role.FLEET_COMPANY.toString()));
        } else if(this.user.getRole().equals(Role.SERVICE_CENTER)) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(Role.SERVICE_CENTER.toString()));
        }
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

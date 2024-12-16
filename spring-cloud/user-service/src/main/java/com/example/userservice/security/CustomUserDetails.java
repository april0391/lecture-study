package com.example.userservice.security;

import com.example.userservice.dto.UserDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    @Getter
    private final UserDto userDto;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return userDto.getEncryptedPwd();
    }

    @Override
    public String getUsername() {
        return userDto.getEmail();
    }
}

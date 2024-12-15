package com.example.userservice.security;

import com.example.userservice.jpa.UserEntity;
import com.example.userservice.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("CustomUserDetailsService.loadUserByUsername");
        UserEntity entity = userRepository.findByEmail(username);
        if (entity == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(entity.getEmail(), entity.getEncryptedPwd(),
                true, true, true, true, new ArrayList<>());
    }
}

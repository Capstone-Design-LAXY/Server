package com.group.laxyapp.config;

import com.group.laxyapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Lazy;

@Component
public class AuthProvider implements AuthenticationProvider {

    private final UserService userService;

    @Autowired
    public AuthProvider(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserDetails userDetails;
        try {
            userDetails = userService.loadUserByUsername(email);
        } catch (UsernameNotFoundException e) {
            throw new AuthenticationException("User not found") {};
        }

        if (!password.equals(userDetails.getPassword())) {
            throw new AuthenticationException("Wrong password") {};
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

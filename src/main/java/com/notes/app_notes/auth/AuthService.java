package com.notes.app_notes.auth;

import com.notes.app_notes.auth.dto.JwtResponse;
import com.notes.app_notes.domain.users.dto.LoginRequest;
import com.notes.app_notes.domain.users.dto.UserRegistrationDto;
import com.notes.app_notes.domain.users.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserServiceImpl userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService CustomUserDetailsService;

    public void register(UserRegistrationDto dto) {
        this.userService.register(dto);
    }

    public JwtResponse authenticate(LoginRequest request) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = this.userService.findByEmail(request.email()).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.email()));
        String jwtToken = this.jwtService.generateToken(
                CustomUserDetailsService.loadUserByUsername(request.email()),
                user.getId().toString()
        );

        return new JwtResponse(user.getId().toString(), user.getEmail(), user.getName(), jwtToken) ;
    }
}
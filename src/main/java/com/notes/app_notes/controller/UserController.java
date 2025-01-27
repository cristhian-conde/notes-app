package com.notes.app_notes.controller;

import com.notes.app_notes.auth.AuthService;
import com.notes.app_notes.auth.dto.JwtResponse;
import com.notes.app_notes.domain.users.dto.LoginRequest;
import com.notes.app_notes.domain.users.dto.UserRegistrationDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

//    private final AuthService authService;
//
//    @PostMapping("/register")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<String> register(@Valid @RequestBody UserRegistrationDto dto) {
//        authService.register(dto);
//        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request) {
//        JwtResponse jwtResponse = authService.authenticate(request);
//        return ResponseEntity.ok(jwtResponse);
//    }
}

package com.notes.app_notes.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtResponse {
    private String id;
    private String email;
    private String username;
    private String access_token;
}
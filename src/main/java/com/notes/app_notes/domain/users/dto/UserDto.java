package com.notes.app_notes.domain.users.dto;

import java.util.UUID;

public record UserDto(
        UUID id,
        String name,
        String email
) {}
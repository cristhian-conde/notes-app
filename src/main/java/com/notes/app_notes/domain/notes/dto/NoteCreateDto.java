package com.notes.app_notes.domain.notes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

public record NoteCreateDto(
        @NotBlank(message = "Title is required")
        @Size(max = 200, message = "Title must be less than 200 characters")
        String title,

        @Size(max = 2500, message = "Content must be less than 5000 characters")
        String content,

        Set<UUID> tagIds
) {}
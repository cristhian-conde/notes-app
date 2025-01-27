package com.notes.app_notes.domain.tags.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TagCreateDto(
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must be less than 100 characters")
        String name
) {}
package com.notes.app_notes.domain.notes.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Set;
import java.util.UUID;

public record NoteUpdateDto(
        @Size(max = 200, message = "Title must be less than 200 characters")
        String title,

        @Size(max = 5000, message = "Content must be less than 5000 characters")
        String content,

        boolean archived,

        Set<UUID> tagIds
) {
        @Builder
        public NoteUpdateDto {}
}
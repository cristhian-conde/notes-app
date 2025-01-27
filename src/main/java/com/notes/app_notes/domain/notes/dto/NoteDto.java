package com.notes.app_notes.domain.notes.dto;

import com.notes.app_notes.domain.tags.dto.TagDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


public record NoteDto(
        UUID id,
        String title,
        String content,
        boolean archived,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Set<TagDto> tags
) {

    @Builder
    public NoteDto {}
}
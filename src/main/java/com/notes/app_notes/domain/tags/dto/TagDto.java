package com.notes.app_notes.domain.tags.dto;

import lombok.Builder;

import java.util.UUID;

public record TagDto(
        UUID id,
        String name
) {
    @Builder
    public TagDto {}
}
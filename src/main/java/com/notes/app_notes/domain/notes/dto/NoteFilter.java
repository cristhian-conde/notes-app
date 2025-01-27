package com.notes.app_notes.domain.notes.dto;

public record NoteFilter(
        String title,
        Boolean archived
) {}
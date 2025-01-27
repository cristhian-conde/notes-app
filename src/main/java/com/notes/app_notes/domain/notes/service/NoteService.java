package com.notes.app_notes.domain.notes.service;

import com.notes.app_notes.domain.notes.dto.*;
import com.notes.app_notes.domain.notes.entity.NotesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

// NoteService.java
public interface NoteService {
    NoteDto createNote(NoteCreateDto dto, UUID userId);
    NoteDto getNoteById(UUID id);
    Page<NoteDto> getAllNotes(Pageable pageable, Boolean archived, UUID userId, String searchTerm);
    NoteDto updateNote(UUID id, NoteUpdateDto dto);
    void deleteNote(UUID id);
}


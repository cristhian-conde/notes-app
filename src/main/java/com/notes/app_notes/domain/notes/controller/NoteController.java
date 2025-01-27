package com.notes.app_notes.domain.notes.controller;

import com.notes.app_notes.auth.JwtService;
import com.notes.app_notes.domain.notes.dto.NoteCreateDto;
import com.notes.app_notes.domain.notes.dto.NoteDto;
import com.notes.app_notes.domain.notes.dto.NoteUpdateDto;
import com.notes.app_notes.domain.notes.service.NoteServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteServiceImpl noteService;
    private final JwtService jwtService;
    private final HttpServletRequest request;

    @PostMapping
    public ResponseEntity<NoteDto> createNote(@Valid @RequestBody NoteCreateDto dto) {
        return ResponseEntity.status(
                HttpStatus.CREATED).body(
                        noteService.createNote(
                                dto,
                                jwtService.extractUUID(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable UUID id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @GetMapping
    public ResponseEntity<Page<NoteDto>> getAllNotes(
            @PageableDefault(sort = "createdAt", direction = DESC) Pageable pageable,
            @RequestParam(required = false) Boolean archived, @RequestParam(required = false) String searchTerm) {
        var userid = jwtService.extractUUID(request);
        return ResponseEntity.ok(noteService.getAllNotes(pageable, archived,userid, searchTerm));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable UUID id, @Valid @RequestBody NoteUpdateDto dto) {
        return ResponseEntity.ok(noteService.updateNote(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable UUID id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
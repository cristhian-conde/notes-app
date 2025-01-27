package com.notes.app_notes.domain.tags.controller;

import com.notes.app_notes.auth.JwtService;
import com.notes.app_notes.domain.tags.dto.TagCreateDto;
import com.notes.app_notes.domain.tags.dto.TagDto;
import com.notes.app_notes.domain.tags.dto.TagUpdateDto;
import com.notes.app_notes.domain.tags.service.TagServiceImpl;
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
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagServiceImpl tagService;
    private final JwtService jwtService;
    private final HttpServletRequest request;

    @PostMapping
    public ResponseEntity<TagDto> createTag(@Valid @RequestBody TagCreateDto dto) {
        UUID userId = jwtService.extractUUID(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.createTag(dto, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getTag(@PathVariable UUID id) {
        return ResponseEntity.ok(tagService.getTagById(id));
    }

    @GetMapping
    public ResponseEntity<Page<TagDto>> getAllTags(
            @PageableDefault(sort = "createdAt", direction = DESC) Pageable pageable) {
        UUID userId = jwtService.extractUUID(request);
        return ResponseEntity.ok(tagService.getAllTags(userId, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDto> updateTag(@PathVariable UUID id, @RequestBody TagUpdateDto dto) {
        return ResponseEntity.ok(tagService.updateTag(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}

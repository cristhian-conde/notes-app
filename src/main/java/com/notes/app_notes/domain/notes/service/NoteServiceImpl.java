package com.notes.app_notes.domain.notes.service;

import com.notes.app_notes.domain.notes.dto.NoteCreateDto;
import com.notes.app_notes.domain.notes.dto.NoteDto;
import com.notes.app_notes.domain.notes.dto.NoteUpdateDto;
import com.notes.app_notes.domain.notes.entity.NotesEntity;
import com.notes.app_notes.domain.notes.repository.NoteRepository;
import com.notes.app_notes.domain.tags.dto.TagDto;
import com.notes.app_notes.domain.tags.entity.TagsEntity;
import com.notes.app_notes.domain.tags.repository.TagRepository;
import com.notes.app_notes.domain.users.entity.UsersEntity;
import com.notes.app_notes.domain.users.service.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final TagRepository tagRepository;
    private final UserServiceImpl userService;

    @Override
    public NoteDto createNote(NoteCreateDto dto, UUID userId) {

        UsersEntity user = userService.findById(userId).orElseThrow(EntityNotFoundException::new);
        List<TagsEntity> tags = tagRepository.findAllById(dto.tagIds());

        NotesEntity note = NotesEntity.builder()
                .title(dto.title())
                .content(dto.content())
                .user(user)
                .tags(new HashSet<>(tags))
                .archived(false)
                .createdBy(userId.toString())
                .build();

        return convertToDto(noteRepository.save(note));
    }

    @Override
    public NoteDto getNoteById(UUID id) {
        return noteRepository.findByIdAndUser(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new EntityNotFoundException("Note not found"));
    }

    @Override
    public Page<NoteDto> getAllNotes(Pageable pageable, Boolean archived, UUID userId, String searchTerm) {
        UsersEntity user = userService.findById(userId).orElseThrow();
        if (archived == null) {
            archived = false;
        }
        if (searchTerm == null) {
            searchTerm = "";
        }
        return noteRepository.findByUserAndArchived(user, archived, searchTerm , pageable)
                .map(this::convertToDto);
    }

    @Override
    public NoteDto updateNote(UUID id, NoteUpdateDto dto) {
        NotesEntity note = noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note not found"));

        updateNoteFromDto(dto, note);
        return convertToDto(noteRepository.save(note));
    }

    @Override
    public void deleteNote(UUID id) {
        NotesEntity note = noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note not found"));
        noteRepository.delete(note);
    }

    public void updateNoteFromDto(NoteUpdateDto dto, NotesEntity note) {
        if (dto.title() != null) note.setTitle(dto.title());
        if (dto.content() != null) note.setContent(dto.content());
        note.setArchived(dto.archived());
        if (dto.tagIds() != null) note.setTags(
                new HashSet<>(tagRepository.findAllById(dto.tagIds()))
        );
        note.setUpdatedBy(note.getCreatedBy());
    }

    private NoteDto convertToDto(NotesEntity note) {
        if (note.getTags() == null) note.setTags(new HashSet<>());
        return NoteDto.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .archived(note.isArchived())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .tags(convertTagsToDto(note.getTags()))
                .build();
    }

    private Set<TagDto> convertTagsToDto(Set<TagsEntity> tags) {
        return tags.stream()
                .map(tag -> TagDto.builder()
                        .id(tag.getId())
                        .name(tag.getName())
                        .build())
                .collect(Collectors.toSet());
    }
}
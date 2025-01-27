package com.notes.app_notes.domain.tags.service;

import com.notes.app_notes.domain.tags.dto.TagCreateDto;
import com.notes.app_notes.domain.tags.dto.TagDto;
import com.notes.app_notes.domain.tags.dto.TagUpdateDto;
import com.notes.app_notes.domain.tags.entity.TagsEntity;
import com.notes.app_notes.domain.tags.repository.TagRepository;
import com.notes.app_notes.domain.users.entity.UsersEntity;
import com.notes.app_notes.domain.users.service.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagServiceImpl {

    private final TagRepository tagRepository;
    private final UserServiceImpl userService;

    public TagDto createTag(TagCreateDto dto, UUID userId) {
        UsersEntity user = userService.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        TagsEntity tag = TagsEntity.builder()
                .name(dto.name())
                .user(user)
                .createdBy(userId.toString())
                .build();

        return convertToDto(tagRepository.save(tag));
    }

    public TagDto getTagById(UUID id) {
        return tagRepository.findByIdAndUser(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new EntityNotFoundException("Etiqueta no encontrada"));
    }

    public Page<TagDto> getAllTags(UUID userId, Pageable pageable) {
        UsersEntity user = userService.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        return tagRepository.findByUser(user, pageable)
                .map(this::convertToDto);
    }

    public TagDto updateTag(UUID id, TagUpdateDto dto) {
        TagsEntity tag = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Etiqueta no encontrada"));

        if (dto.name() != null) {
            tag.setName(dto.name());
        }
        tag.setUpdatedBy(tag.getCreatedBy());
        return convertToDto(tagRepository.save(tag));
    }

    public void deleteTag(UUID id) {
        TagsEntity tag = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Etiqueta no encontrada"));
        tagRepository.delete(tag);
    }

    private TagDto convertToDto(TagsEntity tag) {
        return TagDto.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }
}
package com.notes.app_notes.domain.notes.dto;

import com.notes.app_notes.domain.notes.entity.NotesEntity;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record NoteSearchSpecification(String searchTerm) implements Specification<NotesEntity> {

    @Override
    public Predicate toPredicate(Root<NotesEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.or(
                cb.like(cb.lower(root.get("title")), "%" + searchTerm.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("content")), "%" + searchTerm.toLowerCase() + "%"),
                cb.isMember(searchTerm.toLowerCase(), root.join("tags").get("name"))
        );
    }
}
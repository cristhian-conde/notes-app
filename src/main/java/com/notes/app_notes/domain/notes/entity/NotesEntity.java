package com.notes.app_notes.domain.notes.entity;

import com.notes.app_notes.common.BaseEntity;
import com.notes.app_notes.domain.tags.entity.TagsEntity;
import com.notes.app_notes.domain.users.entity.UsersEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notes")
public class NotesEntity extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private boolean archived;

    @ManyToOne(fetch = FetchType.LAZY)
    private UsersEntity user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<TagsEntity> tags = new HashSet<>();
}
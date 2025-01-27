package com.notes.app_notes.domain.tags.entity;

import com.notes.app_notes.common.BaseEntity;
import com.notes.app_notes.domain.notes.entity.NotesEntity;
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
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TAGS")
public class TagsEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private UsersEntity user;

    @ManyToMany(mappedBy = "tags")
    private Set<NotesEntity> notes = new HashSet<>();

}
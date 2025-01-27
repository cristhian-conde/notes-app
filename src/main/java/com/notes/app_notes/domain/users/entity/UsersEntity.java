package com.notes.app_notes.domain.users.entity;

import com.notes.app_notes.common.BaseEntity;
import com.notes.app_notes.domain.tags.entity.TagsEntity;
import com.notes.app_notes.domain.notes.entity.NotesEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UsersEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<NotesEntity> notes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<TagsEntity> tags;
}


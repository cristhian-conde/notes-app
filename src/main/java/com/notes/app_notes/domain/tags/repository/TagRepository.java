package com.notes.app_notes.domain.tags.repository;

import com.notes.app_notes.domain.tags.entity.TagsEntity;
import com.notes.app_notes.domain.users.entity.UsersEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<TagsEntity, UUID> {
    Page<TagsEntity> findByUser(UsersEntity user, Pageable pageable);


    @Query("SELECT t FROM TagsEntity t WHERE t.id = :id")
    Optional<TagsEntity> findByIdAndUser(
            @Param("id") UUID id
    );
}

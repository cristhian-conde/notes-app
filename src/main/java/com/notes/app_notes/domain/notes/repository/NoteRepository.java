package com.notes.app_notes.domain.notes.repository;

import com.notes.app_notes.domain.notes.entity.NotesEntity;
import com.notes.app_notes.domain.users.entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface NoteRepository extends CrudRepository<NotesEntity, UUID> {

   @Query("SELECT n FROM NotesEntity n " +
           "WHERE n.user = :user " +
           "AND (:archived IS NULL OR n.archived = :archived) " +
           "AND (LOWER(n.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(n.content) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
   Page<NotesEntity> findByUserAndArchived(
           @Param("user") UsersEntity user,
           @Param("archived") Boolean archived,
           @Param("searchTerm") String searchTerm,
           Pageable pageable
   );

   @Query("SELECT n FROM NotesEntity n WHERE n.id = :id")
   Optional<NotesEntity> findByIdAndUser(
           @Param("id") UUID id
   );

}

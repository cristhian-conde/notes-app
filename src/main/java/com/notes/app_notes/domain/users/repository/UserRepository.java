package com.notes.app_notes.domain.users.repository;

import com.notes.app_notes.domain.users.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, UUID> {
    @Query("SELECT u FROM UsersEntity u WHERE LOWER(u.email) = LOWER(:email)")
    Optional<UsersEntity> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);

    // Buscar un usuario por su email y contraseña (para autenticación)
    @Query("SELECT u FROM UsersEntity u WHERE LOWER(u.email) = LOWER(:email) AND u.password = :password")
    Optional<UsersEntity> findByEmailAndPassword(
            @Param("email") String email,
            @Param("password") String password
    );

    @Query("SELECT u FROM UsersEntity u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<UsersEntity> findByNameContainingIgnoreCase(@Param("name") String name);

    long countByDeletedFalse();

    List<UsersEntity> findByDeletedTrue();
}
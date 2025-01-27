package com.notes.app_notes.domain.users.service;

import com.notes.app_notes.domain.users.dto.UserRegistrationDto;
import com.notes.app_notes.domain.users.entity.UsersEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    public UsersEntity register(UserRegistrationDto dto);
    public Optional<UsersEntity> findByEmail(String email);
    public List<UsersEntity> searchByName(String name);
    public long countActiveUsers();
    public Optional<UsersEntity> findById(UUID id);
}


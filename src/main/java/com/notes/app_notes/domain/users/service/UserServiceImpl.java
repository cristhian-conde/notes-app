package com.notes.app_notes.domain.users.service;

import com.notes.app_notes.domain.users.dto.UserRegistrationDto;
import com.notes.app_notes.domain.users.entity.UsersEntity;
import com.notes.app_notes.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public UsersEntity register(UserRegistrationDto dto) {
        if (userRepository.existsByEmailIgnoreCase(dto.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        UsersEntity user = new UsersEntity();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setCreatedBy("SYSTEM");
        return userRepository.save(user);
    }

    @Override
    public Optional<UsersEntity> findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public List<UsersEntity> searchByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public long countActiveUsers() {
        return userRepository.countByDeletedFalse();
    }

    @Override
    public Optional<UsersEntity> findById(UUID id) {
        return userRepository.findById(id);
    }
}

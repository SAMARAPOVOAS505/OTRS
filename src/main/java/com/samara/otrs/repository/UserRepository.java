package com.samara.otrs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samara.otrs.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

    
}

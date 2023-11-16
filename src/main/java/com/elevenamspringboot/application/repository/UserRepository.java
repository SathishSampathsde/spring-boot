package com.elevenamspringboot.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elevenamspringboot.application.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameOrEmail(String username,String email);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);
}

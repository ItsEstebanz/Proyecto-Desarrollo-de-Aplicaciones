package com.ufide.homestore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufide.homestore.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}

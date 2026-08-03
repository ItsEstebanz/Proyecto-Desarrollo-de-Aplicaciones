package com.ufide.homestore.repository;

import com.ufide.homestore.entity.Cart;
import com.ufide.homestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findFirstByUserAndStatusOrderByCreatedAtDesc(User user, String status);
}

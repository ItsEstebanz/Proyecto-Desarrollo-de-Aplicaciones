package com.ufide.homestore.repository;

import com.ufide.homestore.entity.Cart;
import com.ufide.homestore.entity.CartItem;
import com.ufide.homestore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository
        extends JpaRepository<CartItem, Integer> {

    List<CartItem> findByCartOrderByCartItemIdAsc(Cart cart);

    Optional<CartItem> findByCartAndProduct(
            Cart cart,
            Product product
    );
}


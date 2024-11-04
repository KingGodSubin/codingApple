package com.example.shop.item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // extends JpaRepository<Entity명, id컬럼타입>
}

package com.example.shop.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // extends JpaRepository<Entity명, id컬럼타입>
    Page<Item> findPageBy(Pageable page);
}

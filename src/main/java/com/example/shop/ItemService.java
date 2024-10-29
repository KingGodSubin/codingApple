package com.example.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 원래 Service 클래스엔 기본으로 붙여야함
@RequiredArgsConstructor
// 비즈니스 로직 담는 클래스는 Service라고 부름
public class ItemService {

    private final ItemRepository itemRepository;

    public void SaveItem(String title, Integer price){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

}

package com.example.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service // 원래 Service 클래스엔 기본으로 붙여야함
@RequiredArgsConstructor
// 비즈니스 로직 담는 클래스는 Service라고 부름
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(String username, String title, Integer price){
        Item item = new Item();
        item.setUsername(username);
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

    public void editService(Model model, Long itemId){
        Optional<Item> editItem = itemRepository.findById(itemId);
        model.addAttribute("editItem", editItem.get());
    }

    public void editItemService(Item item){
//        Optional<Item> editItem = itemRepository.findById(item.getId());
//        editItem.get().setTitle(item.getTitle());
//        editItem.get().setPrice(item.getPrice());
//        itemRepository.save(editItem.get());
        Item realEditItem = new Item();
        realEditItem.setId(item.getId());
        realEditItem.setTitle(item.getTitle());
        realEditItem.setPrice(item.getPrice());
        itemRepository.save(realEditItem);
    }
}

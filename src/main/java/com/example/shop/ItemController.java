package com.example.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {


    private final ItemRepository itemRepository;

    //    Lombok 없이 등록하려면
//    @Autowired
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    // 템플릿엔진 쓰면 서버/Database의 데이터를 HTML에 집어넣을 수 있음.
    // 이걸 API 라고 하는것 같음
    @GetMapping("/list")
    public String list(Model model) {

        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
//        model.addAttribute("전달할데이터이름", 데이터)
        model.addAttribute("name", "비싼 바지");
        return "list";
    }

    @GetMapping(value = "/write")
    public String write(Item item) {
        return "write";
    }

    @PostMapping(value = "/add")
    // @RequestParam Map formData => input 100개면 100개를 Map 자료형으로 담아줌
    // @ModelAttribute Item item => <input> 데이터들을 바로 object로 변환하려면
    public String addPost(Item item) {
        itemRepository.save(item);
//        var test = new HashMap<>();
//        test.put("name", "kim");
//        test.put("age", 20);
//        System.out.println(test);
        // 특정페이지로 돌아가게 만들 수 있음(redirect)
        return "redirect:/list";
    }

    // URL 파라미터 문법쓰면 비슷한 URL의 API 여러개 만들 필요 없음
    @GetMapping(value = "/detail/{itemId}")
    public String detail(Model model, @PathVariable Long itemId) {

        // Q 왜 Optional 써야함?
        // JPA findById() 만든 사람이 쓰라는데요
        Optional<Item> result = itemRepository.findById(itemId);
        if (result.isPresent()) {
            model.addAttribute("result", result.get());
            return "detail";
        } else {
            return "redirect:/list";
        }

    }


    // HTML에 서버데이터 넣어서 보내주려면
    // 1. Model model 추가
    // 2. model.addAttribute(작명, 데이터)
    // 3. th:text="${작명}"

    // JPA로 데이터 입출력하려면 3-step
    // 1. repository 만들기
    // 2. 원하는 클래스에 repository 등록
    // 3. repository.입출력문법() 쓰기
}

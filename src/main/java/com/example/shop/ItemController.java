package com.example.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    // 템플릿엔진 쓰면 서버/Database의 데이터를 HTML에 집어넣을 수 있음.
    // 이걸 API 라고 하는것 같음
    @GetMapping("/list")
    public String list(Model model){
//        model.addAttribute("전달할데이터이름", 데이터)
        model.addAttribute("name", "비싼 바지");
        return "list.html";
    }
    // HTML에 서버데이터 넣어서 보내주려면
    // 1. Model model 추가
    // 2. model.addAttribute(작명, 데이터)
    // 3. th:text="${작명}"
}

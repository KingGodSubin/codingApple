package com.example.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalTime;

// 메인페이지 접속시 안녕하세요 보내주고 싶다.
// @Controller <- 클래스에 붙이면
@Controller // @ 붙이면 Spring이 알아서 챙겨줌
public class BasicController {

    // 여기서 서버기능 제작 가능
    @GetMapping("/")
//    @ResponseBody <- 문자 그대로 보내주세요
    public String viewMain(){
        return "index";
    };

    @GetMapping("/about") // <- 누가 /about으로 접속하면
    @ResponseBody
    public String about(){
        return "나는 김수빈이야"; // <- 이거 보내주세요~
    }

    @GetMapping("/mypage") // <- 누가 /mypage으로 접속하면
    @ResponseBody
    public String mypage(){
        return "마이페이지입니다~"; // <- 이거 보내주세요~
    }

    @GetMapping("/date")
    @ResponseBody
    public String date(){
        LocalTime nowTime = LocalTime.now();
        LocalDate nowDate = LocalDate.now();

        return "현재시간은 " + nowTime + "\n현재 날짜는 : " + nowDate + "입니다.";
    }

}

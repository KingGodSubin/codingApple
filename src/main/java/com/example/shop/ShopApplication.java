package com.example.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {
    // 웹서버 띄워주세요~
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
        
        // 변수에 들어가 있는 값을 수정하는 것을 막아줌
        // 바뀌면 안되는 값을 설정
        final int wifeCount = 1;
        //        wifeCount = 12;

        String lover = "김말자";
        int age = 27;
        System.out.println(lover);

    }
    // port -> 컴퓨터에 있는 구멍
}

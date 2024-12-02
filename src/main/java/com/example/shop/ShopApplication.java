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
//        final int wifeCount = 1;
//        //        wifeCount = 12;
//
//        String lover = "김말자";
//        int age = 27;
//        System.out.println(lover);
//
//        Test test = new Test();
//        System.out.println(test.name);
//        test.hello();
//
//        Friend friend = new Friend("park");
        // 넷플릭스 Server란? 누가 드라마 줘 하면 드라마를 보내주는 프로그램
        // 서버 개발 특징 : 누가 A 달라고 하면 A 보내주는 코드 짜고 있음.


    }
    // port -> 컴퓨터에 있는 구멍
}

class Test {
    // class는 변수, 함수 보관함임
    String name = "kim";

    void hello() {
        System.out.println("안녕");
    }
}

// 왜 class 쓰는 것임?
// Java는 클래스 강요함
// 관련있는 변수, 함수들 한 곳에 정리가능
// 중요한 변수, 함수 원본 지킬 수 있음

class Friend {
    String friendName = "kim";
    int age = 27;

    Friend(String friendName){
        this.friendName = friendName;
    }
}
package com.example.shop;

import jakarta.persistence.*;

@Entity
public class notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(length = 2000)
    public String title;
    public String date;
    
    // 클래스 변수, 함수 붙이는 modifier
    // 1. public 붙이면 모든 곳에서 사용가능
    // 2. public 안붙이면 package-private(같은 폴더의 클래스에서만 사용가능)
    // 3. private 붙이면 다른 클래스에서 사용불가
    // 4. protected 붙이면 package-private과 같음(예외 : 상속한 클래스는 맘대로 사용가능)
}

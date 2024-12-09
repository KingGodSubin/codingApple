package com.example.shop.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

// 이거 붙이면
@Entity
@Getter
@Setter
@ToString
@Table(indexes = @Index(columnList = "title", name = "작명"))
public class Item { // 이런 이름으로 테이블 하나 생성해줌

    // JPA 라이브러리 덕분에 가능한 것

    @Id // <- 인덱스 컬럼에는 이거 붙이라고 강요함
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <- 알아서 1씩 증가시켜서 넣어줌
    public Long id;

    //    @Column(nullable = false) //
    //    @Column(length = 1000) // String 기본은 255 length 지정하면 직접 지컬럼 비어있으면 테이블에 입력막아주세요.
    //    //    @Column(unique = true) // 이 컬럼값이 유니크하지 않으면 테이블 입력막아줘 가능

    public String title; // 상품명
    
    // 컬럼용 변수에는 int 말고 Integer 강요함
    public Integer price; // 가격
    
    private String username; // 상품을 등록한 유저아이디
    
    private String itemFileOriginName; // 상품파일명
    
    private String itemFilePath; // 상품파일경로(실제 파일 경로)
    
//    // Getter
//    public String getTitle() {
//        return title;
//    }
//
//    // Setter
//    public void setTitle(String title) {
//        this.title = title;
//    }
}

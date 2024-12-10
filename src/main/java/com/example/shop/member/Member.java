package com.example.shop.member;

import com.example.shop.sales.Sales;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 진짜 이름
    private String displayName;
    // 아이디
    @Column(unique = true)
    private String username;
    // 비밀번호
    private String password;

    // 내 정보 훔쳐가는 테이블 행들 자동출력은 @OneToMany
    @ToString.Exclude
    @OneToMany(mappedBy = "member")
    private List<Sales> sales = new ArrayList<>();
}

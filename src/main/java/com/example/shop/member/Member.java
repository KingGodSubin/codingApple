package com.example.shop.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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

}

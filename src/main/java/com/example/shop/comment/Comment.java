package com.example.shop.comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username; // 누가 작성했냐?
    @Column(length = 1000)
    private String content; // 댓글내용
    private Long parentId; // 어떤 게시글의 댓글인지?

}

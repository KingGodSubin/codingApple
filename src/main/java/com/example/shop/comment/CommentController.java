package com.example.shop.comment;

import com.example.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    @PostMapping("/comment")
    @ResponseBody
    String postComment(Authentication auth, Comment comment){
        CustomUser user = (CustomUser) auth.getPrincipal();
        comment.setUsername(user.getUsername());
        commentRepository.save(comment);
        return comment.getContent();
    }
}

package com.example.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(Authentication auth){
        if(auth.isAuthenticated()){
            return "redirect:/list";
        }
        return "register";
    }

    @PostMapping("/member")
    public String addMember(String displayName, String username, String password){
        Member member = new Member();
        member.setDisplayName(displayName);
        member.setUsername(username);
        var hash = passwordEncoder.encode(password);
        member.setPassword(hash);
        memberRepository.save(member);
        return "redirect:/list";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/my-page")
    // Authentication auth로 추가만 해주면 현재 로그인된 사용자의 정보가 다 들어가 있음
    public String myPage(Authentication auth){
        System.out.println(auth.getName());
        CustomUser result = (CustomUser) auth.getPrincipal();
        System.out.println(result.displayName);
        // 현재 로그인 여부
        System.out.println(auth.isAuthenticated());

        return "mypage";
    }

    // @PreAuthorize("isAuthenticated()") <- 로그인 여부
    // @PreAuthorize("isAnonymous()") <- 로그아웃 여부
    // @PreAuthorize("hasAuthority('어쩌구')") <- 권한이 어쩌구면 true 아니면 false
}

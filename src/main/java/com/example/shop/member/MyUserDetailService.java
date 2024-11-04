//package com.example.shop.member;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@RequiredArgsConstructor
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        // DB에서 username을 가진 유저를 찾아와서
//        // return new User(유저아이디, 비번, 권한) 해주세요
//
//        // 1. Spring Security가 내부적으로 비번검사를 해줌(유저가 제출한 비번 == DB에 있던 비번)
//        // 2. Spring Security는 비번이 DB 어디에 저장되있는지 모르기 때문에
//        // 3. 여기에 DB에 있던 비번 찾아주는걸 작성해줌
//
//        Optional<Member> memberRepository.findByUsername(username);
//    }
//
//}

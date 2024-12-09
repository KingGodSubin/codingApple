package com.example.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    
    // 이 함수에 파라미터 하나만 추가해주면 유저가 로그인 시 제출한 ID가 들어가 있음
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // DB에서 username을 가진 유저를 찾아와서
        // return new User(유저아이디, 비번, 권한) 해주세요

        // 1. Spring Security가 내부적으로 비번검사를 해줌(유저가 제출한 비번 == DB에 있던 비번)
        // 2. Spring Security는 비번이 DB 어디에 저장되있는지 모르기 때문에
        // 3. 여기에 DB에 있던 비번 찾아주는걸 작성해줌

        Optional<Member> result = memberRepository.findByUsername(username);
        if(result.isEmpty()){
            throw new UsernameNotFoundException("그런 아이디 없음");
        }
        var user = result.get();
        // 권한 넣기는 List <> <- 타입은 GrantedAuthority 강요
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("일반유저"));
        // return new User(user.getUsername(), user.getPassword(), authorities); -> Controller의 Authentication auth 일로 들어감
        // 근데 이제 사용자의 아이디말고 진짜 이름도 조회해보고 싶은건데
        // new User를 만든 사람은 저것만 쓸 수 있게 만들었음
        // 그럴땐 어카냐
        // User 비슷한 클래스를 내가 만들면 된다
        // return new User(user.getUsername(), user.getPassword(), authorities);
        var a = new CustomUser(user.getUsername(), user.getPassword(), authorities);
        a.displayName = user.getDisplayName();
        a.id = user.getId();
        return a;
    }

}
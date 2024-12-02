package com.example.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

// 아무 클래스에나 이 두 어노테이션을 붙이면 스프링 시큐리티 설정을 만질 수 있음
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // 다른 사이트에서 내 사이트를 원격으로 조작하는걸 막고싶다!
    //추가
//    @Bean
//    public CsrfTokenRepository csrfTokenRepository() {
//        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//        repository.setHeaderName("X-XSRF-TOKEN");
//        return repository;
//    }
    
    // SecurityFilterChain 어떤 페이지를 로그인검사할지 설정가능
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.disable());

//        http.csrf(csrf -> csrf.csrfTokenRepository(csrfTokenRepository())
//                .ignoringRequestMatchers("/login")
//        );

        http.authorizeHttpRequests((authorize) ->
                // 특정 페이지 로그인 검사 할지 결정 가능
                // /** 하면 모든 url의 로그인 검사를 일단 해지
                authorize.requestMatchers("/**").permitAll()
        );

        // 폼으로 로그인하겠다.
        http.formLogin((formLogin)
                // 로그인할 폼은 어딨냐?
                -> formLogin.loginPage("/login")
                // 로그인 성공시 갈 url
                .defaultSuccessUrl("/")
                // 로그인 실패시 갈 url
                // 안적으면 기본적으로 /login?error 페이지로 이동
                // 일종의 query string으로 error가 났다고 알려주는거임
//                .failureUrl("/fail")
        );

        // 로그아웃은
        http.logout(logout -> logout.logoutUrl("/logout"));

        return http.build();
    }
}

// 코드 배치만 하면 로그인기능 끝
// 1. 로그인페이지 만들기
// 2. 폼으로 로그인하겠다고 설정
// 3. DB에 있던 유저 정보 꺼내는 코드
// -> session 방식의 로그인 기능 동작
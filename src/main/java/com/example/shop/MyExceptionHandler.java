package com.example.shop;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler(){
        // 모든 API의 에러를 캐치하려면
        // @ExceptionHandler
        return ResponseEntity.status(400).body("에러남");
    }

}

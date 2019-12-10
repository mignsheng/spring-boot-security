package com.example.demo.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/index")
    public Object index(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

//    @GetMapping("index")
//    public Object index(Authentication authentication) {
//        return authentication;
//    }




}

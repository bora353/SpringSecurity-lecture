package com.example.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;

@org.springframework.stereotype.Controller
public class MainController {

    @GetMapping("/")
    public String main(Model model){
        //id
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        // role
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority authority = iterator.next();
        String role = authority.getAuthority();

        // 전달
        model.addAttribute("id", id);
        model.addAttribute("role", role);

        return "main";
    }

}

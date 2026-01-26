package com.wlfscsg.GroupCamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

    @GetMapping("/")
    public String home(){
        return "index.html";
    }

}

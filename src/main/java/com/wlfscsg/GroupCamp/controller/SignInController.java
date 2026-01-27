package com.wlfscsg.GroupCamp.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignInController {

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    @GetMapping("/login")
    @PostMapping("/login")
    public String loginPage(Model model, HttpServletRequest request) {
        if (isAuthenticated()) {
            return "redirect:home";
        }
        return "index";
    }

    @GetMapping(value = {"/","/home"})
    @PreAuthorize("hasRole('ADMIN')")
    public String home(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession(false);
        String role = (String) session.getAttribute("role");
        if (role != null || role==("ADMIN")){
            return "dashboard";
        }
        return "index";
    }

}

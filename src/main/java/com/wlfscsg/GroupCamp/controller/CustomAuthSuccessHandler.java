package com.wlfscsg.GroupCamp.controller;

import com.wlfscsg.GroupCamp.service.ActivityService;
import com.wlfscsg.GroupCamp.service.PatrolService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Slf4j
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final PatrolService patrolService;

    private final ActivityService activityService;

    public CustomAuthSuccessHandler(PatrolService patrolService, ActivityService activityService) {
        this.patrolService = patrolService;
        this.activityService = activityService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        int totalPatrol = patrolService.fetchPatrolList().size();
        session.setAttribute("totalPatrol", totalPatrol);
        int totalActivity = activityService.fetchActivityList().size();
        session.setAttribute("totalActivity", totalActivity);
        String role = "ROLE_ADMIN";
        session.setAttribute("role", role);
        response.sendRedirect(request.getContextPath()+"/");
    }
}

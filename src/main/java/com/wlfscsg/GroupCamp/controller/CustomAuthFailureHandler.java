package com.wlfscsg.GroupCamp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Slf4j
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String strException = exception.getMessage();
        if (strException != null && !strException.trim().isEmpty()
                && strException.contains("HikariPool_SCOREBOARD")) {
            strException = "Connectivity is down.";
        } else if (strException != null && !strException.trim().isEmpty()
                && strException.contains("Bad credentials")) {
            /*userService.updateUnsuccessHits(request.getParameter("userLoginId"));
            if(userService.isLocked(request.getParameter("userLoginId"))){
                strException = "login.locked_user";
            }else {
                strException = "login.wrong_user_Password";
            }*/
            strException = "login.wrong_user_Password";
        }
        request.getSession(false).setAttribute("error", strException);
        request.getRequestDispatcher("/login").forward(request, response);
    }
}

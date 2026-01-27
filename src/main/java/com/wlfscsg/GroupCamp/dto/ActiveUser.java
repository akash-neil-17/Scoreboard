package com.wlfscsg.GroupCamp.dto;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ActiveUser {

    private ConcurrentHashMap<String, String> users;
    private ConcurrentHashMap<String, HttpSession> userSessions;

    public ActiveUser() {
        users = new ConcurrentHashMap<>();
        userSessions = new ConcurrentHashMap<>();
    }

}

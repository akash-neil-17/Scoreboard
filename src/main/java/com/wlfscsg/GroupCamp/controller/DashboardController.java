package com.wlfscsg.GroupCamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard (){
        return "dashboard";
    }

    @GetMapping("/patrol")
    public String patrol (){
        return "patrolManagement";
    }

    @GetMapping("/activity")
    public String activity (){
        return "activityManagement";
    }

    @GetMapping("/point")
    public String point (){
        return "pointManagement";
    }

    @GetMapping("/individual")
    public String individual () {
        return "individualManagement";
    }

}

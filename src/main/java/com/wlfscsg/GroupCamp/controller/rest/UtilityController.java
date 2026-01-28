package com.wlfscsg.GroupCamp.controller.rest;

import com.wlfscsg.GroupCamp.service.PatrolActivityPointsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class UtilityController {

    private final PatrolActivityPointsService patrolActivityPointsService;

    public UtilityController(PatrolActivityPointsService patrolActivityPointsService) {
        this.patrolActivityPointsService = patrolActivityPointsService;
    }

}

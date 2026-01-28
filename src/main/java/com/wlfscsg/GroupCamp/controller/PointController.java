package com.wlfscsg.GroupCamp.controller;

import com.wlfscsg.GroupCamp.dto.LeaderboardPatrolDTO;
import com.wlfscsg.GroupCamp.dto.PatrolActivityPointSaveDTO;
import com.wlfscsg.GroupCamp.entity.Activity;
import com.wlfscsg.GroupCamp.entity.Patrol;
import com.wlfscsg.GroupCamp.entity.PatrolActivityPoints;
import com.wlfscsg.GroupCamp.model.CommonResponse;
import com.wlfscsg.GroupCamp.service.ActivityService;
import com.wlfscsg.GroupCamp.service.LeaderboardPatrolService;
import com.wlfscsg.GroupCamp.service.PatrolActivityPointsService;
import com.wlfscsg.GroupCamp.service.PatrolService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/point")
public class PointController {

    private final PatrolActivityPointsService patrolActivityPointsService;
    private final PatrolService patrolService;
    private final ActivityService activityService;
    private final LeaderboardPatrolService leaderboardPatrolService;

    public PointController(PatrolActivityPointsService patrolActivityPointsService, PatrolService patrolService, ActivityService activityService, LeaderboardPatrolService leaderboardPatrolService) {
        this.patrolActivityPointsService = patrolActivityPointsService;
        this.patrolService = patrolService;
        this.activityService = activityService;
        this.leaderboardPatrolService = leaderboardPatrolService;
    }

    @GetMapping("/patrol/add")
    public String addPatrolPoints(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        PatrolActivityPointSaveDTO patrolActivityPointSaveDTO = new PatrolActivityPointSaveDTO();
        List<Patrol> patrolList = patrolService.fetchPatrolList();
        List<String> patrolName = new ArrayList<>();
        for (Patrol patrol : patrolList){
            patrolName.add(patrol.getPatrolName());
        }
        List<Activity> activityList = activityService.fetchActivityList();
        List<String> activityName = new ArrayList<>();
        for (Activity activity : activityList){
            activityName.add(activity.getActivityName());
        }
        patrolActivityPointSaveDTO.setPatrolList(patrolName);
        patrolActivityPointSaveDTO.setActivityList(activityName);
        session.setAttribute("patrolName", patrolName);
        session.setAttribute("activityName", activityName);
        model.addAttribute("PATROLACTIVITY", patrolActivityPointSaveDTO);
        return "addPoints-Patrol";
    }

    @PostMapping("/patrol/save")
    public String savePatrolPoint(@ModelAttribute ("PATROLACTIVITY") PatrolActivityPointSaveDTO requestDTO, Model model, HttpServletRequest request, BindingResult result){
        HttpSession session = request.getSession(false);
        PatrolActivityPointSaveDTO saveDTO = new PatrolActivityPointSaveDTO();
        if (requestDTO.getPatrolName().isEmpty()){
            result.rejectValue("patrolName", "error.code", "Please select a patrol name");
        }
        if (requestDTO.getActivityName().isEmpty()){
            result.rejectValue("activityName", "error.code", "Please select an activity");
        }
        int allottedPoint = patrolActivityPointsService.activityPoint(requestDTO.getActivityName());
        if (requestDTO.getEarnedPoint()>allottedPoint){
            result.rejectValue("earnedPoint", "error.code", "Earned point cannot exceed allotted point");
        }
        if (result.hasErrors()){
            model.addAttribute("PATROLACTIVITY", requestDTO);
            return "addPoints-Patrol";
        }
        saveDTO.setPatrolName(requestDTO.getPatrolName());
        saveDTO.setActivityName(requestDTO.getActivityName());
        saveDTO.setEarnedPoint(requestDTO.getEarnedPoint());
        CommonResponse commonResponse = patrolActivityPointsService.savePatrolPointRequest(saveDTO);
        if (commonResponse.equals(CommonResponse.ALREADY_HAS)){
            log.info("Point has already been awarded to the following patrol in the respective activity" + requestDTO.toString());
            result.rejectValue("activityName", "error.code", "Point has already been awarded to the patrol for this activity");
            model.addAttribute("PATROLACTIVITY", requestDTO);
            return "addPoints-Patrol";
        }else if (commonResponse.equals(CommonResponse.SUXS)) {
            log.info("Point has been successfully awarded to the patrol for the following activity" + saveDTO.toString());
            return "addPoints-PatrolSuxs";
        }else {
            log.info("Failed to award point to the patrol for the following activity" + saveDTO.toString());
            return "addPoints-PatrolFail";
        }
    }

    @GetMapping("/patrol/scoreboard")
    public String patrolScoreboard(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
//        List<PatrolActivityPoints> patrolActivityPointsList = patrolActivityPointsService.fetchPatrolActivityPoints();
        List<LeaderboardPatrolDTO> leaderboardPatrol = leaderboardPatrolService.getLeaderboard();
        model.addAttribute("leaderboardPatrol", leaderboardPatrol);
        session.setAttribute("leaderboardPatrol", leaderboardPatrol);
        return "scoreboard-patrol";
    }

    @GetMapping("/individual/add")
    public String addIndividualPoints(Model model, HttpServletRequest request){
        return "addPoints-Individual";
    }

    @PostMapping("/individual/save")
    public String saveIndividualPoint(Model model, HttpServletRequest request, BindingResult result){
        return "addPoints-IndividualSuxs";
    }

    @GetMapping("/individual/scoreboard")
    public String individualScoreboard(Model model, HttpServletRequest request){
        return "scoreboard-individual";
    }

}

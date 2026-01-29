package com.wlfscsg.GroupCamp.controller;

import com.wlfscsg.GroupCamp.dto.ActivitySaveRequestDTO;
import com.wlfscsg.GroupCamp.entity.Activity;
import com.wlfscsg.GroupCamp.model.CommonResponse;
import com.wlfscsg.GroupCamp.service.ActivityService;
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

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/add")
    public String addActivity(Model model, HttpServletRequest request){
        ActivitySaveRequestDTO activitySaveRequestDTO = new ActivitySaveRequestDTO();
        model.addAttribute("ACTIVITY", activitySaveRequestDTO);
        return "addActivity";
    }

    @PostMapping("/save")
    public String saveActivity(@ModelAttribute ("ACTIVITY") ActivitySaveRequestDTO requestDTO, Model model, HttpServletRequest request, BindingResult result){
        HttpSession session = request.getSession(false);
        ActivitySaveRequestDTO saveModel = new ActivitySaveRequestDTO();
        if (requestDTO.getActivityName().isEmpty()){
            result.rejectValue("activityName", "error.code", "Activity Name cannot be empty");
        }
        if (!requestDTO.getActivityName().equals("MISCONDUCT") && requestDTO.getPoint()<0){
            result.rejectValue("point", "error.code", "Point cannot be negative except misconduct");
        }
        if (result.hasErrors()){
            model.addAttribute("ACTIVITY", requestDTO);
            return "addActivity";
        }
        saveModel.setActivityName(requestDTO.getActivityName());
        saveModel.setPoint(requestDTO.getPoint());
        CommonResponse commonResponse = activityService.saveActivityRequest(saveModel);
        if (commonResponse.equals(CommonResponse.ALREADY_HAS)){
            log.info("The following patrol already exists in the Database: " + saveModel.toString());
            result.rejectValue("activityName", "error.code", "Activity Name already exists");
            model.addAttribute("ACTIVITY", requestDTO);
            return "addActivity";
        }else if (commonResponse.equals(CommonResponse.SUXS)){
            log.info(saveModel.toString());
            return "addActivitySuxs";
        }else{
            log.info("Failed to add activity: " + saveModel);
            return "addActivityFail";
        }
    }

    @GetMapping("/view")
    public String viewActivityList(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        List<Activity> activityList = activityService.fetchActivityList();
        session.setAttribute("activityList", activityList);
        return "allActivity";
    }

}

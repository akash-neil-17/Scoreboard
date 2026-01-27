package com.wlfscsg.GroupCamp.controller;

import com.wlfscsg.GroupCamp.dto.PatrolSaveRequestDTO;
import com.wlfscsg.GroupCamp.entity.Patrol;
import com.wlfscsg.GroupCamp.model.CommonResponse;
import com.wlfscsg.GroupCamp.service.PatrolService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/patrol")
public class PatrolController {

    private final PatrolService patrolService;

    public PatrolController(PatrolService patrolService) {
        this.patrolService = patrolService;
    }

    @GetMapping("/add")
    public String addPatrol(Model model, HttpServletRequest request){
        PatrolSaveRequestDTO patrolSaveRequestDTO = new PatrolSaveRequestDTO();
        model.addAttribute("PATROL", patrolSaveRequestDTO);
        return "addPatrol";
    }

    @PostMapping("/save")
    public String savePatrol(@ModelAttribute("PATROL") PatrolSaveRequestDTO patrolSaveRequestDTO, Model model, HttpServletRequest request, BindingResult result){
        HttpSession session = request.getSession(false);
        PatrolSaveRequestDTO saveModel = new PatrolSaveRequestDTO();
        if (patrolSaveRequestDTO.getPatrolUnit()==0){
            result.rejectValue("patrolUnit", "error.code", "Select a Unit Number");
        }
        if (patrolSaveRequestDTO.getPatrolName().isEmpty()){
            result.rejectValue("patrolName", "error.code", "Patrol Name cannot be empty");
        }
        if (result.hasErrors()){
            model.addAttribute("PATROL", patrolSaveRequestDTO);
            return "addPatrol";
        }
        saveModel.setPatrolUnit(patrolSaveRequestDTO.getPatrolUnit());
        saveModel.setPatrolName(patrolSaveRequestDTO.getPatrolName());
        CommonResponse commonResponse = patrolService.savePatrolRequest(saveModel);
        if (commonResponse.equals(CommonResponse.SUXS)){
            log.info(saveModel.toString());
            return "addPatrolSuxs";
        } else if(commonResponse.equals(CommonResponse.ALREADY_HAS)){
            log.info("The following patrol already exists in the Database: " + saveModel.toString());
            result.rejectValue("patrolName", "error.code", "Patrol Name already exists");
            model.addAttribute("PATROL", patrolSaveRequestDTO);
            return "addPatrol";
        }
        else {
            log.info("Failed to add patrol: " + saveModel);
            return "addPatrolFail";
        }
    }

    @GetMapping("/view")
    public String viewPatrolList(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        List<Patrol> patrolList = patrolService.fetchPatrolList();
        session.setAttribute("patrolList", patrolList);
        return "allPatrol";
    }

}

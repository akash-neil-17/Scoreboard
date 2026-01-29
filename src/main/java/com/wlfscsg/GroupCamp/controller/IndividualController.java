package com.wlfscsg.GroupCamp.controller;

import com.wlfscsg.GroupCamp.dto.IndividualSaveRequestDTO;
import com.wlfscsg.GroupCamp.entity.Individual;
import com.wlfscsg.GroupCamp.model.CommonResponse;
import com.wlfscsg.GroupCamp.service.IndividualService;
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
@RequestMapping("/individual")
public class IndividualController {

    private final IndividualService individualService;

    public IndividualController(IndividualService individualService) {
        this.individualService = individualService;
    }

    @GetMapping("/add")
    public String addIndividual(Model model, HttpServletRequest request){
        IndividualSaveRequestDTO saveRequestDTO = new IndividualSaveRequestDTO();
        model.addAttribute("INDIVIDUAL", saveRequestDTO);
        return "addIndividual";
    }

    @PostMapping("/save")
    public String saveIndividual (@ModelAttribute ("INDIVIDUAL") IndividualSaveRequestDTO requestDTO, Model model, HttpServletRequest request, BindingResult result){
        HttpSession session = request.getSession(false);
        IndividualSaveRequestDTO saveModel = new IndividualSaveRequestDTO();
        if (requestDTO.getIndividualName().isEmpty()){
            result.rejectValue("individualName", "error.code", "Name cannot be empty");
        }
        if (requestDTO.getPatrolName().isEmpty()) {
            result.rejectValue("patrolName", "error.code", "Patrol Name cannot be empty");
        }
        if (requestDTO.getMobileNumber().isEmpty()) {
            result.rejectValue("mobileNumber", "error.code", "Mobile Number cannot be empty");
        }
        saveModel.setIndividualName(requestDTO.getIndividualName());
        saveModel.setPatrolName(requestDTO.getPatrolName());
        saveModel.setMobileNumber(requestDTO.getMobileNumber());
        CommonResponse commonResponse = individualService.saveIndividualRequest(saveModel);
        if (commonResponse.equals(CommonResponse.ALREADY_HAS)){
            log.info("The following participant already exists in the Database: " + saveModel.toString());
            result.rejectValue("individualName", "error.code", "Participant already exists");
            model.addAttribute("INDIVIDUAL", requestDTO);
            return "addIndividual";
        }else if (commonResponse.equals(CommonResponse.SUXS)){
            log.info(saveModel.toString());
            return "addIndividualSuxs";
        }else{
            log.info("Failed to add activity: " + saveModel);
            return "addIndividualFail";
        }
    }

    @GetMapping("/view")
    public String viewParticipantList(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        List<Individual> participantList = individualService.fetchIndividualList();
        session.setAttribute("participantList", participantList);
        return "allParticipants";
    }

}

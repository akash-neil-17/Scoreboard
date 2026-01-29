package com.wlfscsg.GroupCamp.controller;

import com.wlfscsg.GroupCamp.dto.IndividualSaveRequestDTO;
import com.wlfscsg.GroupCamp.service.IndividualService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}

package com.codegym.controller;

import com.codegym.model.CareDetail;
import com.codegym.model.CareType;
import com.codegym.model.MyTeam;
import com.codegym.service.CareTypeService;
import com.codegym.service.MyTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private MyTeamService myTeamService;
    @Autowired
    private CareTypeService careTypeService;
    @GetMapping("/home")
    public ModelAndView home(Pageable pageable){
        Page<MyTeam> myTeams = myTeamService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("user/index");
        modelAndView.addObject("myTeams", myTeams);
        return modelAndView;
    }
    @GetMapping("/about")
    public ModelAndView about(Pageable pageable){
        Page<MyTeam> myTeams = myTeamService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("user/about");
        modelAndView.addObject("myTeams", myTeams);
        return modelAndView;
    }
    @GetMapping("/cats")
    public ModelAndView cats(Pageable pageable) {
        Page<CareType> careTypes = careTypeService.findAll (pageable);
        ModelAndView modelAndView = new ModelAndView ("user/cats");
        modelAndView.addObject ("careTypes", careTypes);
        return modelAndView;
    }
    @GetMapping("/contact")
    public String contact(){
        return "user/contact";
    }
    @GetMapping("/dogs")
    public String dogs(){
        return "user/dogs";
    }
    @GetMapping("/volunteer")
    public String volunteer(){
        return "user/volunteer";
    }
}

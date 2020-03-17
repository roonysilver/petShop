package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    private PetTypeService petTypeService;
    @Autowired
    private PetKindService petKindService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private MyTeamService myTeamService;
    @Autowired
    private CareTypeService careTypeService;
    @Autowired
    private CareDetailService careDetailService;
    @Autowired
    private PetService petService;

    @GetMapping("/admin")
    public String admin(){
        return "admin/dashboard";
    }

    @GetMapping(value = "/tables", produces = "application/json;charset=UTF-8")
    public ModelAndView showAdmin(@RequestParam("s") Optional<String> s, Pageable pageable){
        Page<PetType> petTypes;
        Page<PetKind> petKinds;
        Page<PetKind> petKinds1;
        Page<Customer> customers;
        Page<Customer> customers1;
        Page<MyTeam> myTeams;
        Page<MyTeam> myTeams1;
        Page<CareType> careTypes;
        Page<CareDetail> careDetails;
        Page<CareDetail> careDetails1;
        Page<CareDetail> careDetails2;
        Page<Pet> pets;
        Page<Pet> pets1;
        Page<Pet> pets2;
        Page<Pet> pets3;
        if(s.isPresent()){
            petTypes = petTypeService.findAllByPetTypeName(s.get(), pageable);
            petKinds = petKindService.findAllByPetKindName(s.get(), pageable);
            petKinds1 = petKindService.findAllByPetKindStatus(s.get(), pageable);
            customers = customerService.findAllByCustomerName(s.get(), pageable);
            customers1 = customerService.findAllByCustomerNumber(s.get(), pageable);
            myTeams = myTeamService.findAllByTeamName(s.get(), pageable);
            myTeams1 = myTeamService.findAllByTeamNumber(s.get(), pageable);
            careTypes = careTypeService.findAllByCareTypeName(s.get(), pageable);
            careDetails = careDetailService.findAllByCareDetailName(s.get(), pageable);
            careDetails1 = careDetailService.findAllByCareDetailPrice(s.get(), pageable);
            careDetails2 = careDetailService.findAllByCareDetailTime(s.get(), pageable);
            pets = petService.findAllByPetName(s.get(), pageable);
            pets1 = petService.findAllByPetAge(s.get(), pageable);
            pets2 = petService.findAllByPetWeight(s.get(), pageable);
            pets3 = petService.findAllByPetColor(s.get(), pageable);
        }else {
            petTypes = petTypeService.findAll(pageable);
            petKinds = petKindService.findAll(pageable);
            petKinds1 = petKindService.findAll(pageable);
            customers = customerService.findAll(pageable);
            customers1 = customerService.findAll(pageable);
            myTeams = myTeamService.findAll(pageable);
            myTeams1= myTeamService.findAll(pageable);
            careTypes = careTypeService.findAll(pageable);
            careDetails = careDetailService.findAll(pageable);
            careDetails1 = careDetailService.findAll(pageable);
            careDetails2 = careDetailService.findAll(pageable);
            pets = petService.findAll(pageable);
            pets1 = petService.findAll(pageable);
            pets2 = petService.findAll(pageable);
            pets3 = petService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("admin/tables");
        modelAndView.addObject("petTypes", petTypes);
        modelAndView.addObject("petKinds", petKinds);
        modelAndView.addObject("petKinds", petKinds1);
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("customers", customers1);
        modelAndView.addObject("myTeams", myTeams);
        modelAndView.addObject("myTeams", myTeams1);
        modelAndView.addObject("careTypes", careTypes);
        modelAndView.addObject("careDetails", careDetails);
        modelAndView.addObject("careDetails", careDetails1);
        modelAndView.addObject("careDetails", careDetails2);
        modelAndView.addObject("pets", pets);
        modelAndView.addObject("pets", pets1);
        modelAndView.addObject("pets", pets2);
        modelAndView.addObject("pets", pets3);
        return modelAndView;
    }
}

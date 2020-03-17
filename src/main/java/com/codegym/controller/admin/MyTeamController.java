package com.codegym.controller.admin;

import com.codegym.model.MyTeam;
import com.codegym.service.MyTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class MyTeamController {
    @Autowired
    private MyTeamService myTeamService;
    @GetMapping(value = "/create-myTeam", produces = "application/json;charset=UTF-8")
    public ModelAndView showFormMyTeam() {
        ModelAndView modelAndView = new ModelAndView("admin/myTeam/create");
        modelAndView.addObject("myTeam", new MyTeam());
        return modelAndView;
    }

    @PostMapping(value = "/create-myTeam", produces = "application/json;charset=UTF-8")
    public ModelAndView saveMyTeam(@Valid @ModelAttribute("myTeam")MyTeam myTeam, BindingResult bindingResult){
        new MyTeam().validate(myTeam, bindingResult);
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("admin/myTeam/create");
            return modelAndView;
        } else {
            myTeamService.save(myTeam);
            ModelAndView modelAndView = new ModelAndView("admin/myTeam/create");
            modelAndView.addObject("myTeam", myTeam);
            modelAndView.addObject("message","New MyTeam create successfully");
            return modelAndView;
        }
    }

    @GetMapping(value = "/edit-myTeam/{id}", produces = "application/json;charset=UTF-8")
    public ModelAndView showEditForm(@PathVariable Long id){
        MyTeam myTeam = myTeamService.findById(id);
        if (myTeam != null){
            ModelAndView modelAndView = new ModelAndView("admin/myTeam/edit");
            modelAndView.addObject("myTeam", myTeam);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/edit-myTeam", produces = "application/json;charset=UTF-8")
    public ModelAndView updateMyTeam(@Valid @ModelAttribute("myTeam") MyTeam myTeam, BindingResult bindingResult) {
        new MyTeam().validate(myTeam, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("admin/myTeam/edit");
            return modelAndView;
        } else {
            myTeamService.save(myTeam);
            ModelAndView modelAndView = new ModelAndView("admin/myTeam/edit");
            modelAndView.addObject("myTeam", myTeam);
            modelAndView.addObject("message", "MyTeam updated successfully");
            return modelAndView;
        }
    }

    @GetMapping(value = "/delete-myTeam/{id}", produces = "application/json;charset=UTF-8")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        MyTeam myTeam = (MyTeam) myTeamService.findById(id);
        if(myTeam != null) {
            ModelAndView modelAndView = new ModelAndView("/admin/myTeam/delete");
            modelAndView.addObject("myTeam", myTeam);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/delete-myTeam", produces = "application/json;charset=UTF-8")
    public String deleteMyTeam(@ModelAttribute("myTeam") MyTeam myTeam){
        myTeamService.remove(myTeam.getId());
        return "redirect:tables";
    }
}

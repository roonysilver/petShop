package com.codegym.controller.admin;

import com.codegym.model.CareType;
import com.codegym.service.CareTypeService;
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
public class CareTypeController {
    @Autowired
    private CareTypeService careTypeService;
    @GetMapping(value = "/create-careType", produces = "application/json;charset=UTF-8")
    public ModelAndView showFormCareType() {
        ModelAndView modelAndView = new ModelAndView("admin/careType/create");
        modelAndView.addObject("careType", new CareType());
        return modelAndView;
    }

    @PostMapping(value = "/create-careType", produces = "application/json;charset=UTF-8")
    public ModelAndView saveCareType(@Valid @ModelAttribute("careType")CareType careType, BindingResult bindingResult){
        new CareType().validate(careType, bindingResult);
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("admin/careType/create");
            return modelAndView;
        } else {
            careTypeService.save(careType);
            ModelAndView modelAndView = new ModelAndView("admin/careType/create");
            modelAndView.addObject("careType", careType);
            modelAndView.addObject("message","New Care Type create successfully");
            return modelAndView;
        }
    }

    @GetMapping(value = "/edit-careType/{id}", produces = "application/json;charset=UTF-8")
    public ModelAndView showEditForm(@PathVariable Long id){
        CareType careType = careTypeService.findById(id);
        if (careType != null){
            ModelAndView modelAndView = new ModelAndView("admin/careType/edit");
            modelAndView.addObject("careType", careType);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/edit-careType", produces = "application/json;charset=UTF-8")
    public ModelAndView updateCareType(@Valid @ModelAttribute("careType") CareType careType, BindingResult bindingResult) {
        new CareType().validate(careType, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("admin/careType/edit");
            return modelAndView;
        } else {
            careTypeService.save(careType);
            ModelAndView modelAndView = new ModelAndView("admin/careType/edit");
            modelAndView.addObject("careType", careType);
            modelAndView.addObject("message", "Care Type updated successfully");
            return modelAndView;
        }
    }

    @GetMapping(value = "/delete-careType/{id}", produces = "application/json;charset=UTF-8")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        CareType careType = (CareType) careTypeService.findById(id);
        if(careType != null) {
            ModelAndView modelAndView = new ModelAndView("/admin/careType/delete");
            modelAndView.addObject("careType", careType);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/delete-careType", produces = "application/json;charset=UTF-8")
    public String deleteCareType(@ModelAttribute("careType") CareType careType){
        careTypeService.remove(careType.getId());
        return "redirect:tables";
    }
}

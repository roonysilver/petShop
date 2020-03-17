package com.codegym.controller.admin;

import com.codegym.model.PetType;
import com.codegym.service.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class PetTypeController {
    @Autowired
    private PetTypeService petTypeService;

    @GetMapping(value = "/create-petType", produces = "application/json;charset=UTF-8")
    public ModelAndView showFormPetType() {
        ModelAndView modelAndView = new ModelAndView("admin/petType/create");
        modelAndView.addObject("petType", new PetType());
        return modelAndView;
    }

    @PostMapping(value = "/create-petType", produces = "application/json;charset=UTF-8")
    public ModelAndView savePetType(@Valid @ModelAttribute("petType")PetType petType, BindingResult bindingResult){
        new PetType().validate(petType, bindingResult);
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("admin/petType/create");
            return modelAndView;
        } else {
            petTypeService.save(petType);
            ModelAndView modelAndView = new ModelAndView("admin/petType/create");
            modelAndView.addObject("petType", petType);
            modelAndView.addObject("message","New Pet Type create successfully");
            return modelAndView;
        }
    }

    @GetMapping(value = "/edit-petType/{id}", produces = "application/json;charset=UTF-8")
    public ModelAndView showEditForm(@PathVariable Long id){
        PetType petType = petTypeService.findById(id);
        if (petType != null){
            ModelAndView modelAndView = new ModelAndView("admin/petType/edit");
            modelAndView.addObject("petType", petType);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/edit-petType", produces = "application/json;charset=UTF-8")
    public ModelAndView updatePetType(@Valid @ModelAttribute("petType") PetType petType, BindingResult bindingResult) {
        new PetType().validate(petType, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("admin/petType/edit");
            return modelAndView;
        } else {
            petTypeService.save(petType);
            ModelAndView modelAndView = new ModelAndView("admin/petType/edit");
            modelAndView.addObject("petType", petType);
            modelAndView.addObject("message", "Pet Type updated successfully");
            return modelAndView;
        }
    }

    @GetMapping(value = "/delete-petType/{id}", produces = "application/json;charset=UTF-8")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        PetType petType = (PetType) petTypeService.findById(id);
        if(petType != null) {
            ModelAndView modelAndView = new ModelAndView("/admin/petType/delete");
            modelAndView.addObject("petType", petType);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/delete-petType", produces = "application/json;charset=UTF-8")
    public String deletePetType(@ModelAttribute("petType") PetType petType){
        petTypeService.remove(petType.getId());
        return "redirect:tables";
    }
}

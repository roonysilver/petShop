package com.codegym.controller.admin;

import com.codegym.model.PetKind;
import com.codegym.model.PetType;
import com.codegym.service.PetKindService;
import com.codegym.service.PetKindService;
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
public class PetKindController {
    @Autowired
    private PetKindService petKindService;
    @Autowired
    private PetTypeService petTypeService;
    @ModelAttribute("petTypes")
    public Page<PetType> petTypes(Pageable pageable){
        return petTypeService.findAll(pageable);
    }

    @GetMapping(value = "/create-petKind", produces = "application/json;charset=UTF-8")
    public ModelAndView showFormPetKind() {
        ModelAndView modelAndView = new ModelAndView("admin/petKind/create");
        modelAndView.addObject("petKind", new PetKind());
        return modelAndView;
    }

    @PostMapping(value = "/create-petKind", produces = "application/json;charset=UTF-8")
    public ModelAndView savePetKind(@Valid @ModelAttribute("petKind")PetKind petKind, BindingResult bindingResult){
        new PetKind().validate(petKind, bindingResult);
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("admin/petKind/create");
            return modelAndView;
        } else {
            petKindService.save(petKind);
            ModelAndView modelAndView = new ModelAndView("admin/petKind/create");
            modelAndView.addObject("petKind", petKind);
            modelAndView.addObject("message","New Pet Kind create successfully");
            return modelAndView;
        }
    }

    @GetMapping(value = "/edit-petKind/{id}", produces = "application/json;charset=UTF-8")
    public ModelAndView showEditForm(@PathVariable Long id){
        PetKind petKind = petKindService.findById(id);
        if (petKind != null){
            ModelAndView modelAndView = new ModelAndView("admin/petKind/edit");
            modelAndView.addObject("petKind", petKind);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/edit-petKind", produces = "application/json;charset=UTF-8")
    public ModelAndView updatePetKind(@Valid @ModelAttribute("petKind") PetKind petKind, BindingResult bindingResult) {
        new PetKind().validate(petKind, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("admin/petKind/edit");
            return modelAndView;
        } else {
            petKindService.save(petKind);
            ModelAndView modelAndView = new ModelAndView("admin/petKind/edit");
            modelAndView.addObject("petKind", petKind);
            modelAndView.addObject("message", "Pet Kind updated successfully");
            return modelAndView;
        }
    }

    @GetMapping(value = "/delete-petKind/{id}", produces = "application/json;charset=UTF-8")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        PetKind petKind = (PetKind) petKindService.findById(id);
        if(petKind != null) {
            ModelAndView modelAndView = new ModelAndView("/admin/petKind/delete");
            modelAndView.addObject("petKind", petKind);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/delete-petKind", produces = "application/json;charset=UTF-8")
    public String deletePetKind(@ModelAttribute("petKind") PetKind petKind){
        petKindService.remove(petKind.getId());
        return "redirect:tables";
    }
}

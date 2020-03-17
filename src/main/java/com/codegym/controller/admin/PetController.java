package com.codegym.controller.admin;

import com.codegym.model.Customer;
import com.codegym.model.MyTeam;
import com.codegym.model.Pet;
import com.codegym.model.PetKind;
import com.codegym.service.CustomerService;
import com.codegym.service.MyTeamService;
import com.codegym.service.PetKindService;
import com.codegym.service.PetService;
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
public class PetController {
    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;
    @ModelAttribute("customers")
    public Page<Customer> customers(Pageable pageable){
        return customerService.findAll(pageable);
    }

    @Autowired
    private PetKindService petKindService;
    @ModelAttribute("petKinds")
    public Page<PetKind> petKinds(Pageable pageable){return petKindService.findAll(pageable);}

    @Autowired
    private MyTeamService myTeamService;
    @ModelAttribute("myTeams")
    public Page<MyTeam> myTeams(Pageable pageable){return myTeamService.findAll(pageable);}

    @GetMapping(value = "/create-pet", produces = "application/json;charset=UTF-8")
    public ModelAndView showFormPet() {
        ModelAndView modelAndView = new ModelAndView("admin/pet/create");
        modelAndView.addObject("pet", new Pet());
        return modelAndView;
    }

    @PostMapping(value = "/create-pet", produces = "application/json;charset=UTF-8")
    public ModelAndView savePet(@Valid @ModelAttribute("pet")Pet pet, BindingResult bindingResult){
        new Pet().validate(pet, bindingResult);
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("admin/pet/create");
            return modelAndView;
        } else {
            petService.save(pet);
            ModelAndView modelAndView = new ModelAndView("admin/pet/create");
            modelAndView.addObject("pet", pet);
            modelAndView.addObject("message","New Pet create successfully");
            return modelAndView;
        }
    }

    @GetMapping(value = "/edit-pet/{id}", produces = "application/json;charset=UTF-8")
    public ModelAndView showEditForm(@PathVariable Long id){
        Pet pet = petService.findById(id);
        if (pet != null){
            ModelAndView modelAndView = new ModelAndView("admin/pet/edit");
            modelAndView.addObject("pet", pet);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/edit-pet", produces = "application/json;charset=UTF-8")
    public ModelAndView updatePet(@Valid @ModelAttribute("pet") Pet pet, BindingResult bindingResult) {
        new Pet().validate(pet, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("admin/pet/edit");
            return modelAndView;
        } else {
            petService.save(pet);
            ModelAndView modelAndView = new ModelAndView("admin/pet/edit");
            modelAndView.addObject("pet", pet);
            modelAndView.addObject("message", "Pet updated successfully");
            return modelAndView;
        }
    }

    @GetMapping(value = "/delete-pet/{id}", produces = "application/json;charset=UTF-8")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Pet pet = (Pet) petService.findById(id);
        if(pet != null) {
            ModelAndView modelAndView = new ModelAndView("/admin/pet/delete");
            modelAndView.addObject("pet", pet);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/delete-pet", produces = "application/json;charset=UTF-8")
    public String deletePet(@ModelAttribute("pet") Pet pet){
        petService.remove(pet.getId());
        return "redirect:tables";
    }
}

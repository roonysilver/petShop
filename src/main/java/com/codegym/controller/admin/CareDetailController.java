package com.codegym.controller.admin;

import com.codegym.model.CareDetail;
import com.codegym.model.CareType;
import com.codegym.model.PetType;
import com.codegym.service.CareDetailService;
import com.codegym.service.CareTypeService;
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
public class CareDetailController {
    @Autowired
    private CareDetailService careDetailService;
    @Autowired
    private CareTypeService careTypeService;
    @ModelAttribute("careTypes")
    public Page<CareType> careTypes(Pageable pageable){
        return careTypeService.findAll(pageable);
    }


    @GetMapping(value = "/create-careDetail", produces = "application/json;charset=UTF-8")
    public ModelAndView showFormCareDetail() {
        ModelAndView modelAndView = new ModelAndView("admin/careDetail/create");
        modelAndView.addObject("careDetail", new CareDetail());
        return modelAndView;
    }

    @PostMapping(value = "/create-careDetail", produces = "application/json;charset=UTF-8")
    public ModelAndView saveCareDetail(@Valid @ModelAttribute("careDetail")CareDetail careDetail, BindingResult bindingResult){
        new CareDetail().validate(careDetail, bindingResult);
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("admin/careDetail/create");
            return modelAndView;
        } else {
            careDetailService.save(careDetail);
            ModelAndView modelAndView = new ModelAndView("admin/careDetail/create");
            modelAndView.addObject("careDetail", careDetail);
            modelAndView.addObject("message","New Care Detail create successfully");
            return modelAndView;
        }
    }

    @GetMapping(value = "/edit-careDetail/{id}", produces = "application/json;charset=UTF-8")
    public ModelAndView showEditForm(@PathVariable Long id){
        CareDetail careDetail = careDetailService.findById(id);
        if (careDetail != null){
            ModelAndView modelAndView = new ModelAndView("admin/careDetail/edit");
            modelAndView.addObject("careDetail", careDetail);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/edit-careDetail", produces = "application/json;charset=UTF-8")
    public ModelAndView updateCareDetail(@Valid @ModelAttribute("careDetail") CareDetail careDetail, BindingResult bindingResult) {
        new CareDetail().validate(careDetail, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("admin/careDetail/edit");
            return modelAndView;
        } else {
            careDetailService.save(careDetail);
            ModelAndView modelAndView = new ModelAndView("admin/careDetail/edit");
            modelAndView.addObject("careDetail", careDetail);
            modelAndView.addObject("message", "Care Detail updated successfully");
            return modelAndView;
        }
    }

    @GetMapping(value = "/delete-careDetail/{id}", produces = "application/json;charset=UTF-8")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        CareDetail careDetail = (CareDetail) careDetailService.findById(id);
        if(careDetail != null) {
            ModelAndView modelAndView = new ModelAndView("/admin/careDetail/delete");
            modelAndView.addObject("careDetail", careDetail);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/delete-careDetail", produces = "application/json;charset=UTF-8")
    public String deleteCareDetail(@ModelAttribute("careDetail") CareDetail careDetail){
        careDetailService.remove(careDetail.getId());
        return "redirect:tables";
    }
}

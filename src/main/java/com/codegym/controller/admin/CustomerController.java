package com.codegym.controller.admin;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
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
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping(value = "/create-customer", produces = "application/json;charset=UTF-8")
    public ModelAndView showFormCustomer() {
        ModelAndView modelAndView = new ModelAndView("admin/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping(value = "/create-customer", produces = "application/json;charset=UTF-8")
    public ModelAndView saveCustomer(@Valid @ModelAttribute("customer")Customer customer, BindingResult bindingResult){
        new Customer().validate(customer, bindingResult);
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("admin/customer/create");
            return modelAndView;
        } else {
            customerService.save(customer);
            ModelAndView modelAndView = new ModelAndView("admin/customer/create");
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("message","New Customer create successfully");
            return modelAndView;
        }
    }

    @GetMapping(value = "/edit-customer/{id}", produces = "application/json;charset=UTF-8")
    public ModelAndView showEditForm(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        if (customer != null){
            ModelAndView modelAndView = new ModelAndView("admin/customer/edit");
            modelAndView.addObject("customer", customer);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/edit-customer", produces = "application/json;charset=UTF-8")
    public ModelAndView updateCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        new Customer().validate(customer, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("admin/customer/edit");
            return modelAndView;
        } else {
            customerService.save(customer);
            ModelAndView modelAndView = new ModelAndView("admin/customer/edit");
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("message", "Customer updated successfully");
            return modelAndView;
        }
    }

    @GetMapping(value = "/delete-customer/{id}", produces = "application/json;charset=UTF-8")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Customer customer = (Customer) customerService.findById(id);
        if(customer != null) {
            ModelAndView modelAndView = new ModelAndView("/admin/customer/delete");
            modelAndView.addObject("customer", customer);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/delete-customer", produces = "application/json;charset=UTF-8")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer){
        customerService.remove(customer.getId());
        return "redirect:tables";
    }
}

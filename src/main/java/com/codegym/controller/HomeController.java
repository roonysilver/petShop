package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(){
        return "user/index";
    }
    @GetMapping("/about")
    public String about(){
        return "user/about";
    }
    @GetMapping("/cats")
    public String cats(){
        return "user/cats";
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

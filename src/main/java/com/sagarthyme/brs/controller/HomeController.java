package com.sagarthyme.brs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String baseUrl(){
        return "redirect:/home-page";
    }

    @GetMapping("home-page")
    public String homePage(){
        return "homepage";
    }
}

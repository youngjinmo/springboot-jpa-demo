package com.inflearn.hellospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {
    @GetMapping("hello/{guest}")
    public String hello(@PathVariable String guest, Model model){
        model.addAttribute("guest", guest);
        return "hello";
    }
}

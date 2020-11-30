package com.inflearn.hellospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello/{guest}")
    public String hello(@PathVariable String guest, Model model){
        model.addAttribute("guest", guest);
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-mvc";
    }

    @GetMapping("hello-mvc2")
    @ResponseBody
    public String helloMvc2(@RequestParam("name") String name, Model model) {
        // @ResponseBody
        // return 을 그대로 화면에 출력하는 메서드
        return "hello-mvc"+name;
    }
}

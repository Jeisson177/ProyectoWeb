package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Index")
public class MainController {

    @GetMapping
    @ResponseBody
    public String index() {
        return "<h1>Hello World</h1>";
    }
}
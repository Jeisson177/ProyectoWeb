package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homeAdmin")
public class AdminController {

    @GetMapping
    public String mostrarHomeAdmin() {
        return "homeAdmin";
    }

    @RequestMapping("/homeAdmin")
    public String homeAdmin() {
        return "homeAdmin";
    }

    @RequestMapping("/usuarios")
    public String usuarios() {
        return "redirect:/clientes";
    }

    @RequestMapping("/productos")
    public String productos() {
        return "redirect:/producto/pr";
    }

    @RequestMapping("/adicionales")
    public String ventas() {
        return "redirect:/adicionales";
    }
}

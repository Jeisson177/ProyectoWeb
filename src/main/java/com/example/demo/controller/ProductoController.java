package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class ProductoController {

    // Carga la página HTML de menú
    @GetMapping
    public String mostrarMenu(Model model) {
        return "menu"; // Carga el archivo menu.html desde templates
    }
}

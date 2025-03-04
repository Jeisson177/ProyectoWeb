package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ProductoService;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String mostrarMenu(Model model) {
        model.addAttribute("productos", productoService.getAllProductos()); // Solo 1 línea para agregar productos
        return "menu";  // Retorna la vista sin más lógica extra
    }
}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ProductoService;

@Controller
@RequestMapping("/menu")
public class ProductoController {
    @Autowired
    ProductoService productoService;
    // Carga la página HTML de menú
    @GetMapping
    public String mostrarMenu(Model model) {
        model.addAttribute("productos", productoService.getAllProductos());
        return "menu"; // Carga el archivo menu.html desde templates
    }
    @GetMapping("/producto")
    public String mostrarProductos(Model model){
        model.addAttribute("productos", productoService.getAllProductos());
        return "menuTabla";
    
    }
}

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
    @GetMapping
    public String mostrarMenu(Model model) {
        model.addAttribute("productos", productoService.getAllProductos());
        return "menu"; 
    }
    @GetMapping("/producto")
    public String mostrarProductos(Model model){
        model.addAttribute("productos", productoService.getAllProductos());
        return "adicionales_Tabla";
    
    }
}

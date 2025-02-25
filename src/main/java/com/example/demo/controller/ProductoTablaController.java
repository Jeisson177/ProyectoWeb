package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import Services.ProductoTablaServicioImp;


import org.springframework.web.bind.annotation.GetMapping;



@RequestMapping("/productoTabla")
@Controller
public class ProductoTablaController {

    @Autowired
    private ProductoTablaServicioImp productoTablaServicioImp;

    @GetMapping("/all")
    public String mostrarTodosProductos(Model model) {

        model.addAttribute("productos", productoTablaServicioImp.SearchAll());
        return "MenuTabla";
    }
    
}

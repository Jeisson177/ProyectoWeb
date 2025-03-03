package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ProductoServiceImp;

@Controller
@RequestMapping("/producto")
public class ProductoTablaController {

    @Autowired
    ProductoServiceImp productoService;
    @GetMapping("/pr")
    public String mostrarProductos(Model model){
        model.addAttribute("productos", productoService.getAllProductos());
        return "menuTabla";
    
    }
    
}

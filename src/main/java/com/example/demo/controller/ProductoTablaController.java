package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.ProductoRepository;


import org.springframework.web.bind.annotation.GetMapping;



@RequestMapping("/productoTabla")
@Controller
public class ProductoTablaController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/menuTabla")
    public String getAllProductos(Model model) {
    try {
        model.addAttribute("productos", productoRepository.getAllProductos());
        return "menuTabla";
    } catch (Exception e) {
        System.out.println(e);
        return "error";
    }
    }
    
}

package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Producto;
import com.example.demo.service.ProductoServiceImp;

@Controller
@RequestMapping("/producto") // La URL cambia a "/producto/{id}"
public class EspecificoController {

    private final ProductoServiceImp productoService;

    public EspecificoController(ProductoServiceImp productoService) {
        this.productoService = productoService;
    }

    // Controlador para la vista de detalles con Thymeleaf
    @GetMapping("/{id}")
    public String obtenerProductoPorId(@PathVariable Long id, Model model) {
        Producto producto = productoService.getProductoById(id);
    
        if (producto == null) {
            model.addAttribute("producto", null);
        } else {
            model.addAttribute("producto", producto);
            model.addAttribute("adicionales", producto.getAdicionales()); // Enviar adicionales a la vista
        }
    
        return "detalle"; // Retorna la plantilla detalle.html
    }

}

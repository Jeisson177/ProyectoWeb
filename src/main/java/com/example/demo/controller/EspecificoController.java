package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Producto;
import com.example.demo.service.ProductoServiceImp;

@Controller
@RequestMapping("/producto") 
public class EspecificoController {

    private final ProductoServiceImp productoService;

    public EspecificoController(ProductoServiceImp productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/{id}")
    public String obtenerProductoPorId(@PathVariable Long id, Model model) {
        Producto producto = productoService.getProductoById(id);

        if (producto == null) {
            return "redirect:/error"; // Redirigir a una página de error si no encuentra el producto
        }

        model.addAttribute("producto", producto);
        model.addAttribute("adicionales", producto.getAdicionales());

        return "detalle"; // Thymeleaf cargará 'detalle.html'
    }
}

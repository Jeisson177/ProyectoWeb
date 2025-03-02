package com.example.demo.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.ProductoService;
import com.example.demo.service.ProductoServiceImp;

// Controlador para el JSON
@RestController
@RequestMapping("/api")
public class ProductoRestController {

    @Autowired
    ProductoServiceImp productoService;

    // Retorna el JSON con todos los productos
    @GetMapping("/menu")
    public Collection<Producto> getProductos() {
        return productoService.getAllProductos();
    }
}

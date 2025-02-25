package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;

// Controlador para el JSON
@RestController
@RequestMapping("/api")
public class ProductoRestController {

    @Autowired
    private ProductoRepository productoRepository;

    // Retorna el JSON con todos los productos
    @GetMapping("/menu")
    public Map<Integer, Producto> getAllProductos() {
        return  productoRepository.getAllProductos();
    }
}

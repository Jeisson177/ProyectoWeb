package com.example.demo.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Producto;
import com.example.demo.service.ProductoServiceImp;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") // Permitir llamadas desde el frontend
public class EspecificoController {

    private final ProductoServiceImp productoService;

    public EspecificoController(ProductoServiceImp productoService) {
        this.productoService = productoService;
    }

    // Obtener todos los productos
    @GetMapping
    public Collection<Producto> obtenerTodosLosProductos() {
        return productoService.getAllProductos();
    }

    // Obtener detalle de un producto por ID
    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable int id) {
        return productoService.getProductoById(id);
    }
}

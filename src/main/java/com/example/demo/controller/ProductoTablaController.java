package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Adicional;
import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.AdicionalService;
import com.example.demo.service.ProductoServiceImp;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200")

public class ProductoTablaController {
    @Autowired
    private ProductoServiceImp productoService;

    @Autowired
    private AdicionalService adicionalService;

    // Obtener todos los productos
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    // Obtener un producto por ID con sus adicionales
    @GetMapping("/{id}")
    public Producto getProductoConAdicionales(@PathVariable Long id) {
        return productoService.getProductoWithAdicionales(id);
    }

    // Crear nuevo producto
    @PostMapping
    public void guardarProducto(@RequestBody Producto producto) {
        productoService.guardarProducto(producto);
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public void actualizarProducto(@PathVariable Long id,@RequestBody Producto productoConAdicionales) {
        List<Long> idsAdicionales = new ArrayList<>();
        if (productoConAdicionales.getAdicionales() != null) {
            for (Adicional adicional : productoConAdicionales.getAdicionales()) {
                idsAdicionales.add(adicional.getAdicional_id()); // Asegúrate que los adicionales tengan ID
            }
        }

        productoConAdicionales.setProducto_id(id); // Establecer el ID correctamente
        List<Adicional> adicionales = adicionalService.getAdicionalesByIds(idsAdicionales);
        productoService.actualizarProducto(productoConAdicionales, adicionales);
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
    }
}

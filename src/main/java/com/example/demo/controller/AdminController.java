package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Adicional;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Producto;
import com.example.demo.service.AdicionalService;
import com.example.demo.service.ClienteService;
import com.example.demo.service.ProductoService;

@RestController
@RequestMapping("/Admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
    @Autowired
    ClienteService clienteService;
    @Autowired
    ProductoService productoService;

    @Autowired
    AdicionalService adicionalService;
    
    @GetMapping("/usuarios")
    public List<Cliente> getUsuarios() {
        return clienteService.obtenerTodosLosClientes();
    }

    @GetMapping("/productos")
    public List<Producto> getProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/adicionales")
    public List<Adicional> getAdicionales() {
        return adicionalService.getAllAdicionales();
    }
}

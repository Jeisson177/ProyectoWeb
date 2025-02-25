package com.example.demo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;
@Service
public class ProductoServiceImp implements ProductoService{
    @Autowired
    ProductoRepository productoRepository;


    @Override
    public Collection<Producto> getAllProductos() {
        return productoRepository.getAllProductos().values();
    }

    @Override
    public Producto getProductoById(int id) {
        return productoRepository.getProducto(id);
    }
    
    }
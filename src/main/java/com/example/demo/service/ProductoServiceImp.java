package com.example.demo.service;

import java.util.Collection;
import java.util.List;

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
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).get();
    }
    @Override
    public List<Producto> getProductosPorTipo(String tipo) {
        return productoRepository.buscarPorTipo(tipo);
    }
    
    }
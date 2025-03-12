package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Adicional;
import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }
    
    @Override
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @   Override
    public void guardarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    @Transactional
    public void actualizarProducto(Producto productoNuevo,List<Adicional> adicionales) {
        Producto producto = productoRepository.findById(productoNuevo.getProducto_ID()).orElse(null);
        producto.setNombre(productoNuevo.getNombre());
        producto.setPrecio(productoNuevo.getPrecio());
        producto.setDescripcion(productoNuevo.getDescripcion());
        producto.setTipo(productoNuevo.getTipo());
        producto.getAdicionales().clear();
        producto.getAdicionales().addAll(adicionales);
        productoRepository.save(producto);
        Producto producto2=productoRepository.findById(producto.getProducto_ID()).orElse(null);
        int a=1;
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}

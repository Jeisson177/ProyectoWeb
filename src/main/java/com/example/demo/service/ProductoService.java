package com.example.demo.service;

import java.util.Collection;

import com.example.demo.entity.Producto;

public interface ProductoService {
    Collection<Producto> getAllProductos();
    Producto getProductoById(Long id);
    void guardarProducto(Producto producto);
    void actualizarProducto(Producto producto);
    void eliminarProducto(Long id);
}

package com.example.demo.service;

import java.util.Collection;
import java.util.List;

import com.example.demo.entity.Adicional;
import com.example.demo.entity.Producto;

public interface ProductoService {
    List<Producto> getAllProductos();
    Producto getProductoById(Long id);
    void guardarProducto(Producto producto);
    void actualizarProducto(Producto productoNuevo, List<Adicional> adicionales);
    void eliminarProducto(Long id);
    Producto getProductoWithAdicionales(Long id);
}

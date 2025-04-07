package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Adicional;
import com.example.demo.entity.Producto;

public interface ProductoService {
    List<Producto> getAllProductos();
    Producto getProductoById(Long id);
    Producto guardarProducto(Producto producto);
    Optional<Producto> actualizarProducto(Producto productoNuevo, List<Adicional> adicionales);
    void eliminarProducto(Long id);
    Producto getProductoWithAdicionales(Long id);
}

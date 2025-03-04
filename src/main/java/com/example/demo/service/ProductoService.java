package com.example.demo.service;

import java.util.Collection;
import java.util.List;

import com.example.demo.entity.Producto;

public interface ProductoService {
    Collection<Producto> getAllProductos();
    Producto getProductoById(Long id);
    List<Producto> getProductosPorTipo(String tipo);

}

package com.example.demo.service;

import java.util.ArrayList;
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
    public void actualizarProducto(Producto producto) {
        try {
            if (productoRepository.existsById(producto.getProducto_ID())) {
                Producto productoExistente = productoRepository.findById(producto.getProducto_ID()).orElse(null);
                if (productoExistente != null) {
                    // Actualizar los campos básicos
                    productoExistente.setNombre(producto.getNombre());
                    productoExistente.setDescripcion(producto.getDescripcion());
                    productoExistente.setPrecio(producto.getPrecio());
                    productoExistente.setTipo(producto.getTipo());
    
                    // Crear una nueva lista de adicionales
                    List<Adicional> nuevosAdicionales = new ArrayList<>(producto.getAdicionales());
    
                    // Obtener una copia de los adicionales existentes
                    List<Adicional> adicionalesExistentes = new ArrayList<>(productoExistente.getAdicionales());
    
                    // Eliminar los adicionales que ya no están en la nueva lista
                    for (Adicional existente : adicionalesExistentes) {
                        if (!nuevosAdicionales.contains(existente)) {
                            productoExistente.getAdicionales().remove(existente); // Eliminar de la lista
                            existente.setProducto(null); // Romper la relación bidireccional
                        }
                    }
    
                    // Añadir los nuevos adicionales uno a uno
                    for (Adicional nuevoAdicional : nuevosAdicionales) {
                        if (!productoExistente.getAdicionales().contains(nuevoAdicional)) {
                            nuevoAdicional.setProducto(productoExistente); // Establecer la relación bidireccional
                            productoExistente.getAdicionales().add(nuevoAdicional); // Añadir el adicional a la lista
                        }
                    }
    
                    // Guardar el producto actualizado
                    productoRepository.saveAndFlush(productoExistente);
                }
            } else {
                throw new RuntimeException("El producto no existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el producto: " + e.getMessage());
        }
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}

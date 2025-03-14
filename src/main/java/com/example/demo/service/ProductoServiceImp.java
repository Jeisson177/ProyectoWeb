package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Adicional;
import com.example.demo.entity.Producto;
import com.example.demo.repository.AdicionalRepository;
import com.example.demo.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private AdicionalRepository adicionalRepository;
    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }
    
    @Override
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto getProductoWithAdicionales(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    @Transactional
    public void actualizarProducto(Producto productoNuevo, List<Adicional> adicionales) {
        Producto producto = productoRepository.findById(productoNuevo.getProducto_id()).orElse(null);

        if (producto != null) {
            // Actualizar los atributos básicos del producto
            producto.setNombre(productoNuevo.getNombre());
            producto.setPrecio(productoNuevo.getPrecio());
            producto.setDescripcion(productoNuevo.getDescripcion());
            producto.setCategoria(productoNuevo.getCategoria());

            // Actualizar la relación ManyToMany
            producto.getAdicionales().clear();
            productoRepository.save(producto); // Guardar sin adicionales primero para limpiar la tabla intermedia

            if (adicionales != null && !adicionales.isEmpty()) {
                for (Adicional adicional : adicionales) {
                    Adicional adicionalPersistido = adicionalRepository.findById(adicional.getAdicional_id()).orElse(null);
                    if (adicionalPersistido != null) {
                        producto.getAdicionales().add(adicionalPersistido);
                    }
                }
            }

            // Guardar el producto actualizado con los nuevos adicionales
            productoRepository.save(producto);
        }
    }


    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
    

    
}

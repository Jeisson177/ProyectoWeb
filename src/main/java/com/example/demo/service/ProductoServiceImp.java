package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Adicional;
import com.example.demo.entity.Producto;
import com.example.demo.repository.AdicionalRepository;
import com.example.demo.repository.ProductoRepository;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private AdicionalRepository adicionalRepository;

    // Inyectar el EntityManager
    @PersistenceContext
    private EntityManager entityManager; 

    @Override
    public List<Producto> getAllProductos() {
    return productoRepository.findByTemporadaTrue(); 
    }


    @Override
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto getProductoWithAdicionales(Long id) {
        // Usando un EntityGraph para cargar explícitamente los adicionales
        EntityGraph<Producto> graph = entityManager.createEntityGraph(Producto.class);
        graph.addSubgraph("adicionales"); // Especificamos que queremos cargar la relación adicionales
    
        // Usar una consulta JPQL para recuperar el producto con sus adicionales
        Producto producto = entityManager.createQuery(
            "SELECT p FROM Producto p LEFT JOIN FETCH p.adicionales WHERE p.producto_id = :id", Producto.class)
            .setParameter("id", id)
            .setHint("jakarta.persistence.fetchgraph", graph) // Aquí indicamos que use el EntityGraph
            .getSingleResult();
        
        return producto;
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public Optional<Producto> actualizarProducto(Producto productoNuevo, List<Adicional> adicionales) {
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
            Producto actualizado = productoRepository.save(producto);
            return Optional.of(actualizado);        
        }
        return Optional.empty();
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}

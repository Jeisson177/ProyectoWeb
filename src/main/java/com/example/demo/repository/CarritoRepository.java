package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Carrito;

import jakarta.transaction.Transactional;



public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    // Buscar carrito por ID de cliente
    Optional<Carrito> findByClienteId(Long clienteId);

    // Verificar existencia por ID de cliente
    boolean existsByClienteId(Long clienteId);

    // Eliminar carrito por ID de cliente (transaccional)
    @Transactional
    @Modifying
    @Query("DELETE FROM Carrito c WHERE c.clienteId = :clienteId")
    void deleteByClienteId(Long clienteId);

    // Buscar carrito con items cargados (usando fetch join)
    @Query("SELECT c FROM Carrito c LEFT JOIN FETCH c.items WHERE c.id = :id")
    Optional<Carrito> findByIdWithItems(Long id);

    // Buscar carrito por cliente con items cargados
    @Query("SELECT c FROM Carrito c LEFT JOIN FETCH c.items WHERE c.clienteId = :clienteId")
    Optional<Carrito> findByClienteIdWithItems(Long clienteId);
}
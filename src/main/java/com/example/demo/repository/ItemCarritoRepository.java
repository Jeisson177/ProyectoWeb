package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ItemCarrito;

@Repository
public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Long> {

    public Optional<ItemCarrito> findByCarritoIdAndProductoId(Long id, Long productoId);

    public Optional<ItemCarrito> findByIdAndCarritoId(Long itemCarritoId, Long id);


    public void deleteByCarritoId(Long id);

    public List<ItemCarrito> findByCarritoIdWithProductAndAdicionales(Long id);

    
}
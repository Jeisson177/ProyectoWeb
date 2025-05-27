package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ItemCarrito;

@Repository
public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Long> {
    @Query("SELECT i FROM ItemCarrito i WHERE i.carrito.id = :carritoId AND i.producto.producto_id = :productoId")
    public Optional<ItemCarrito> findByCarritoIdAndProductoId(Long carritoId, Long productoId);

    public Optional<ItemCarrito> findByIdAndCarritoId(Long itemCarritoId, Long carritoId);


    public void deleteByCarritoId(Long carritoId);

    @Query("SELECT i FROM ItemCarrito i " +
       "JOIN FETCH i.producto " +
       "LEFT JOIN FETCH i.adicionales " +
       "WHERE i.carrito.id = :carritoId")
    public List<ItemCarrito> findByCarritoIdWithProductAndAdicionales(@Param("carritoId") Long carritoId);


    @EntityGraph(attributePaths = {"producto", "adicionales"})
    public List<ItemCarrito> findByCarritoId(Long carritoId);

    
}
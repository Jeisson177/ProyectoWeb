package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "items_carrito")
public class ItemCarrito {

    public ItemCarrito(Carrito aThis, Producto producto1, int cantidad1, List<Adicional> adicionales1) {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private int cantidad;

    @ManyToMany
    @JoinTable(
        name = "item_carrito_adicionales",
        joinColumns = @JoinColumn(name = "item_id"),
        inverseJoinColumns = @JoinColumn(name = "adicional_id")
    )
    private List<Adicional> adicionales = new ArrayList<>();

    public BigDecimal calcularSubtotal() {
        BigDecimal precioBase = BigDecimal.valueOf(producto.getPrecio());
        BigDecimal precioAdicionales = adicionales.stream()
                .map(a -> BigDecimal.valueOf(a.getPrecio()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return precioBase.add(precioAdicionales).multiply(BigDecimal.valueOf(cantidad));
    }

    // Getters y Setters

    Object getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
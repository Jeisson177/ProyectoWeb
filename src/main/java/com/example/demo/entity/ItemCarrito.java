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
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrito_id", nullable = false)
    private Carrito carrito;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;
    
    private int cantidad;
    
    @ManyToMany
    @JoinTable(
        name = "item_carrito_adicionales",
        joinColumns = @JoinColumn(name = "item_id"),
        inverseJoinColumns = @JoinColumn(name = "adicional_id")
    )
    private List<Adicional> adicionales = new ArrayList<>();
    
    // Constructor protegido para JPA
    protected ItemCarrito() {}
    
    // Constructor principal
    public ItemCarrito(Carrito carrito, Producto producto, int cantidad, List<Adicional> adicionales) {
        this.carrito = carrito;
        this.producto = producto;
        this.cantidad = cantidad;
        this.adicionales = adicionales;
    }
    
    public BigDecimal calcularSubtotal() {
        BigDecimal precioBase = BigDecimal.valueOf(producto.getPrecio());
        BigDecimal precioAdicionales = adicionales.stream()
            .map(a -> BigDecimal.valueOf(a.getPrecio()))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return precioBase.add(precioAdicionales).multiply(BigDecimal.valueOf(cantidad));
    }
    
    // Getters
    public Long getId() { return id; }
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public List<Adicional> getAdicionales() { return adicionales; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setAdicionales(List<Adicional> adicionales) {
        this.adicionales = adicionales;
    }

    
}


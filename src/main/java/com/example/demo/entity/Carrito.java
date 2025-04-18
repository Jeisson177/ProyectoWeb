package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carritos")
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrito> items = new ArrayList<>();

    public Carrito() {
        // Constructor vacío necesario para JPA
    }

    public Carrito(Long clienteId) {
        this.clienteId = clienteId;
    }

    public void agregarItem(Producto producto, int cantidad, List<Adicional> adicionales) {
        ItemCarrito item = new ItemCarrito(this, producto, cantidad, adicionales);
        this.items.add(item);
    }

    public void removerItem(Long itemId) {
        items.removeIf(item -> item.getId().equals(itemId));
    }

    public void limpiarCarrito() {
        items.clear();
    }

    public BigDecimal calcularTotal() {
        return items.stream()
                .map(ItemCarrito::calcularSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }
}
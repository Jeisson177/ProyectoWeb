package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
    
    // Métodos utilitarios
    public void agregarItem(Producto producto, int cantidad, List<Adicional> adicionales) {
        ItemCarrito item = new ItemCarrito(this, producto, cantidad, adicionales);
        items.add(item);
    }
    
    public void limpiarCarrito() {
        items.clear();
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public Long getClienteId() { return clienteId; }
    public List<ItemCarrito> getItems() { return items; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItems(List<ItemCarrito> items) {
        this.items = items;
    }
}


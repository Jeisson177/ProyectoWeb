package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "carritos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
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
    
   
}


package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Data
public class Adicional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adicional_id;

    
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;  // Cambié la "N" a minúscula (convención Java)

    
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private int cantidad;

    @Min(value = 0, message = "El precio no puede ser negativo")
    private int precio;

    @ManyToMany(mappedBy = "adicionales", cascade = { CascadeType.MERGE})
    @JsonIgnoreProperties("adicionales") // <- opcional
    private List<Producto> productos;

    private boolean temporada = true;

     public Adicional() {this.productos = new ArrayList<>();
    }

    public boolean isTemporada() {
        return temporada;
    }

    public void setTemporada(boolean temporada) {
        this.temporada = temporada;
    }

    public Adicional(String nombre, int cantidad, int precio) {
        this.productos = new ArrayList<>();
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.temporada = true;
    }

    public List<Producto> getProductos() {
        return productos;
    }
    public void agregarProducto(Producto producto) {
        if (!this.productos.contains(producto)) {
            this.productos.add(producto);
            producto.getAdicionales().add(this); // Relación bidireccional
        }
    }
    public void quitarAdicional(Producto producto) {
        productos.remove(producto);
        producto.getAdicionales().remove(this);
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public int getPrecio() {
        return precio;
    }
}

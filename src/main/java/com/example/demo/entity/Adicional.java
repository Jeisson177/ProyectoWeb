package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Adicional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adicional_id;

    private String nombre;  // Cambié la "N" a minúscula (convención Java)
    private int cantidad;
    private int precio;

    @ManyToMany(mappedBy = "adicionales", cascade = { CascadeType.MERGE})
    @JsonIgnore
    private List<Producto> productos = new ArrayList<>();

    public Adicional() {}

    public Adicional(String nombre, int cantidad, int precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
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
    public Long getAdicional_id() {
        return adicional_id;
    }

    public void setAdicional_id(Long adicional_id) {
        this.adicional_id = adicional_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }



    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}

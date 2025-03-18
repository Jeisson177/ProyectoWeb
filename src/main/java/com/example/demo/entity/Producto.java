package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long producto_id;

    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @Size(min = 3, max = 150, message = "la descripcion debe tener entre 3 y 150 caracteres")
    private String descripcion;

    @Min(value = 0, message = "El precio no puede ser negativo")
    private int precio;

    @Size(min = 3, max = 20, message = "La categoria debe tener entre 3 y 20 caracteres")
    private String categoria;
    
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
        name = "producto_adicional", 
        joinColumns = @JoinColumn(name = "producto_id"), 
        inverseJoinColumns = @JoinColumn(name = "adicional_id")
    )
    @JsonIgnore
    private List<Adicional> adicionales = new ArrayList<>();

    public Producto() {}

    public Producto(String nombre, int precio, String descripcion, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }
    public void agregarAdicional(Adicional adicional) {
        if (!this.adicionales.contains(adicional)) {
            this.adicionales.add(adicional);
            adicional.getProductos().add(this); // Relación bidireccional
        }
    }

    public void quitarAdicional(Adicional adicional) {
        this.adicionales.remove(adicional);
        adicional.getProductos().remove(this);
    }

    public Long getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Long producto_id) {
        this.producto_id = producto_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }


    public List<Adicional> getAdicionales() {
        return adicionales;
    }

    public void setAdicionales(List<Adicional> adicionales) {
        this.adicionales = adicionales;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}

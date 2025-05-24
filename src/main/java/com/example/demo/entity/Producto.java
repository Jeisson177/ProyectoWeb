package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
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
    
    private boolean temporada;

    public boolean isTemporada() {
        return temporada;
    }

    public void setTemporada(boolean temporada) {
        this.temporada = temporada;
    }

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
        name = "producto_adicional", 
        joinColumns = @JoinColumn(name = "producto_id"), 
        inverseJoinColumns = @JoinColumn(name = "adicional_id")
    )
    @JsonIgnoreProperties("productos") // <- esto evita la recursión infinita
    private List<Adicional> adicionales = new ArrayList<>();

    public Producto() {}

    public Producto(String nombre, int precio, String descripcion, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.temporada = true;
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

    
}

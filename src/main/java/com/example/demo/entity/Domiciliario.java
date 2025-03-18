package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Domiciliario {

    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @Pattern(regexp = "\\d{10}", message = "El teléfono debe tener 10 dígitos")
    @Column(nullable = false)
    private int celular;

    @Column(nullable = false)
    private boolean disponibilidad;

    @Id
    @GeneratedValue
    private Long id; 

    public Domiciliario(Long id, String nombre, int celular, boolean disponibilidad) {
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.disponibilidad = disponibilidad;
    }

    public Domiciliario(String nombre, int celular, boolean disponibilidad) {
        this.nombre = nombre;
        this.celular = celular;
        this.disponibilidad = disponibilidad;
    }

    public Domiciliario() {}
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCelular() {
        return celular;
    }
    public void setCelular(int celular) {
        this.celular = celular;
    }
    public boolean isdisponibilidad() {
        return disponibilidad;
    }
    public void setdisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

}
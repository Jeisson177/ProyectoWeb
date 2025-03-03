package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Domiciliario {
    private String nombre;
    private int celular;
    private boolean Disponibilidad;

    @Id
    @GeneratedValue
    private Long id; 

    public Domiciliario(Long id, String nombre, int celular, boolean Disponibilidad) {
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.Disponibilidad = Disponibilidad;
    }

    public Domiciliario(String nombre, int celular, boolean Disponibilidad) {
        this.nombre = nombre;
        this.celular = celular;
        this.Disponibilidad = Disponibilidad;
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
    public boolean isDisponibilidad() {
        return Disponibilidad;
    }
    public void setDisponibilidad(boolean disponibilidad) {
        this.Disponibilidad = disponibilidad;
    }

}
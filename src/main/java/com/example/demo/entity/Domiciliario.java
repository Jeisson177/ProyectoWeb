package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Data
@NoArgsConstructor

public class Domiciliario {

    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @Pattern(regexp = "\\d{10}", message = "El teléfono debe tener 10 dígitos")
    @Column(nullable = false)
    private String celular;

    @Column(nullable = false)
    private boolean disponibilidad;

    @Id
    @GeneratedValue
    private Long id; 

    @OneToMany(mappedBy = "domiciliario")
    @JsonIgnore
    private List<Pedido> pedidos;
    
    public Domiciliario(Long id, String nombre, String celular, boolean disponibilidad) {
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.disponibilidad = disponibilidad;
    }

    public Domiciliario(String nombre, String celular, boolean disponibilidad) {
        this.nombre = nombre;
        this.celular = celular;
        this.disponibilidad = disponibilidad;
    }

    
    public boolean isdisponibilidad() {
        return disponibilidad;
    }
    public void setdisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCelular() {
        return celular;
    }

     public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

}
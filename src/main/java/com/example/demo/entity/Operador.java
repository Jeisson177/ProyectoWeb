package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "operadores")
@Data
public class Operador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String usuario;
    private String contrasena;
    private boolean disponible;

    @OneToMany(mappedBy = "operador")
    @JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();

    // Constructores
    public Operador() {
    }

    public Operador(String nombre, String usuario, String contrasena, boolean disponible) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.disponible = disponible;
    }

   

    public String getcontrasena() {
        return contrasena;
    }

    public void setcontrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    
}
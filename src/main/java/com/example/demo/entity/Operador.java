package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Operador {
    private String nombre;
    private String usuario;
    private String contrasena;

    @Id
    @GeneratedValue
    private Long idOperador;

    public Operador(Long idOperador, String nombre, String usuario, String contrasena) {
        this.idOperador = idOperador;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Operador(String nombre, String usuario, String contrasena) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Operador() {}

    public Long getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(Long idOperador) {
        this.idOperador = idOperador;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public String getusuario() {
        return usuario;
    }

    public void setusuario(String usuario) {
        this.usuario = usuario;
    }

    public String getcontrasena() {
        return contrasena;
    }

    public void setcontrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Operador {
    private String Nombre;
    private String Usuario;
    private String Contrasena;

    @Id
    @GeneratedValue
    private Long idOperador;

    public Operador(Long idOperador, String Nombre, String Usuario, String Contrasena) {
        this.idOperador = idOperador;
        this.Nombre = Nombre;
        this.Usuario = Usuario;
        this.Contrasena = Contrasena;
    }

    public Operador(String Nombre, String Usuario, String Contrasena) {
        this.Nombre = Nombre;
        this.Usuario = Usuario;
        this.Contrasena = Contrasena;
    }

    public Operador() {}

    public Long getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(Long idOperador) {
        this.idOperador = idOperador;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

}
package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Administrador {
    private String Nombre;
    private String Apellido;
    private String Usuario;
    private String Contrasena;

    @Id
    @GeneratedValue
    private Long administrador_ID;

    public Administrador(Long administrador_ID, String Nombre, String Apellido, String Usuario, String Contrasena) {
        this.administrador_ID = administrador_ID;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Usuario = Usuario;
        this.Contrasena = Contrasena;
    }

    public Administrador( String Nombre, String Apellido, String Usuario, String Contrasena) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Usuario = Usuario;
        this.Contrasena = Contrasena;
    }

    public Administrador() {}
    
    public Long getAdministrador_ID() {
        return administrador_ID;
    }
    public void setAdministrador_ID(Long administrador_ID) {
        this.administrador_ID = administrador_ID;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    public String getApellido() {
        return Apellido;
    }
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
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

package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Administrador {
    private String nombre;
    private String apellido;
    private String usuario;
    private String contrasena;

    @Id
    @GeneratedValue
    private Long administrador_ID;

    public Administrador(Long administrador_ID, String nombre, String apellido, String usuario, String contrasena) {
        this.administrador_ID = administrador_ID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Administrador( String nombre, String apellido, String usuario, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Administrador() {}
    
    public Long getAdministrador_ID() {
        return administrador_ID;
    }
    public void setAdministrador_ID(Long administrador_ID) {
        this.administrador_ID = administrador_ID;
    }
    public String getnombre() {
        return nombre;
    }
    public void setnombre(String nombre) {
        this.nombre = nombre;
    }
    public String getapellido() {
        return apellido;
    }
    public void setapellido(String apellido) {
        this.apellido = apellido;
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

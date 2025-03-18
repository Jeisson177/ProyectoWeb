package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Cliente {

    private String Nombre;
    private String Apellido;
    
    @Column(name = "correo")
    private String correo;

    private String contrasena;
    private String Direccion;
    private String Telefono;

    @Id
    @GeneratedValue
    private Long id;

    public Cliente(Long id, String Nombre, String Apellido, String correo, String contrasena, String Direccion, String Telefono) {
        this.id = id;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
    }

    public Cliente( String Nombre, String Apellido, String correo, String contrasena, String Direccion, String Telefono) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
    }

    public Cliente() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }


}
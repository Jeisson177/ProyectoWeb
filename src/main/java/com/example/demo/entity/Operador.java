package com.example.demo.entity;


public class Operador {

    private int idOperador;
    private String Nombre;
    private String Usuario;
    private String Contrasena;

    public Operador(int idOperador, String Nombre, String Usuario, String Contrasena) {
        this.idOperador = idOperador;
        this.Nombre = Nombre;
        this.Usuario = Usuario;
        this.Contrasena = Contrasena;
    }

    public int getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(int idOperador) {
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
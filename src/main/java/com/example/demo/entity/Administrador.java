package com.example.demo.entity;

public class Administrador {
    private int administrador_ID;
    private String Nombre;
    private String Apellido;
    private String Usuario;
    private String Contrasena;

    public Administrador(int administrador_ID, String Nombre, String Apellido, String Usuario, String Contrasena) {
        this.administrador_ID = administrador_ID;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Usuario = Usuario;
        this.Contrasena = Contrasena;
    }
    public int getAdministrador_ID() {
        return administrador_ID;
    }
    public void setAdministrador_ID(int administrador_ID) {
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

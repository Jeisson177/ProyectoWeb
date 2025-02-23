package com.example.demo.entity;

public class Domiciliario {
    private int id; 
    private String nombre;
    private int celular;
    private boolean Disponibilidad;

    public Domiciliario(int id, String nombre, int celular, boolean Disponibilidad) {
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.Disponibilidad = Disponibilidad;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
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
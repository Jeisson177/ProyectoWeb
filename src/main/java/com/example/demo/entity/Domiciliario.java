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
    

}
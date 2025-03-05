package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Adicional {
    private String Nombre;
    private int cantidad;
    private int precio;

    @Id
    @GeneratedValue
    private Long adicional_id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = true)
    @JsonBackReference  // Evita que se serialice nuevamente el producto
    private Producto producto;

    public Adicional(Long adicional_id,  int cantidad, String Nombre, int precio, Producto producto) {
        this.producto = producto;
        this.adicional_id = adicional_id;
        this.cantidad = cantidad;
        this.Nombre = Nombre;
        this.precio = precio;
        
    }

    public Adicional(int cantidad, String Nombre, int precio, Producto producto) {
        this.cantidad = cantidad;
        this.Nombre = Nombre;
        this.precio = precio;
        this.producto = producto;
    }
    

    public Adicional() {}

    public Long getAdicional_id() {
        return adicional_id;
    }

    public void setAdicional_id(Long adicional_id) {
        this.adicional_id = adicional_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }


}
package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
public class Producto {

    private String Nombre;
    private int Precio;
    private String Descripcion;
    private String Tipo;

    @Id
    @GeneratedValue
    private Long producto_ID;

    @ManyToMany(mappedBy = "producto")
    private List<Adicional> adicionales = new ArrayList<>();

    public Producto(Long producto_ID, String Nombre, int Precio, String Descripcion, String Tipo) {
        this.producto_ID = producto_ID;
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.Descripcion = Descripcion;
        this.Tipo = Tipo;
    }

    public Producto( String Nombre, int Precio, String Descripcion, String Tipo) {
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.Descripcion = Descripcion;
        this.Tipo = Tipo;
    }

    public Producto() {}

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    public Long getProducto_ID() {
        return producto_ID;
    }

    public void setProducto_ID(Long producto_ID) {
        this.producto_ID = producto_ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int Precio) {
        this.Precio = Precio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    public List<Adicional> getAdicionales() {
        return adicionales;
    }

    public void setAdicionales(List<Adicional> adicionales) {
        this.adicionales = adicionales;
    }
    
}

package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long producto_ID;

    private String Nombre;
    private int Precio;
    private String Descripcion;
    private String Tipo;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Adicional> adicionales = new ArrayList<>();

    public Producto() {}

    public Producto(String Nombre, int Precio, String Descripcion, String Tipo) {
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.Descripcion = Descripcion;
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

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public List<Adicional> getAdicionales() {
        return adicionales;
    }

    public void setAdicionales(List<Adicional> adicionales) {
        this.adicionales = adicionales;
    }

    public void addAdicional(Adicional adicional) {
        adicionales.add(adicional);
        adicional.setProducto(this);
    }

    public void removeAdicional(Adicional adicional) {
        adicionales.remove(adicional);
        adicional.setProducto(null);
    }
}

package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Producto {

    
    private int pedido_ID;
    private String Nombre;
    private int Precio;
    private String Descripcion;
    private String Tipo;

    @Id
    @GeneratedValue
    private Long producto_ID;

    @ManyToMany(mappedBy = "producto")
    private List<Adicional> adicionales = new ArrayList<>();

    public Producto(Long producto_ID, int pedido_ID, String Nombre, int Precio, String Descripcion, String Tipo) {
        this.producto_ID = producto_ID;
        this.pedido_ID = pedido_ID;
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.Descripcion = Descripcion;
        this.Tipo = Tipo;
    }

    public Producto( int pedido_ID, String Nombre, int Precio, String Descripcion, String Tipo) {
        this.pedido_ID = pedido_ID;
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

    public int getPedido_ID() {
        return pedido_ID;
    }

    public void setPedido_ID(int pedido_ID) {
        this.pedido_ID = pedido_ID;
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

    
}

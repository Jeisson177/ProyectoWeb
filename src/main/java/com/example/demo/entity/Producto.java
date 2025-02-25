package com.example.demo.entity;

public class Producto {

    private int producto_ID;
    private int pedido_ID;
    private String Nombre;
    private int Precio;
    private String Descripcion;
    private String Tipo;

    public Producto(int producto_ID, int pedido_ID, String Nombre, int Precio, String Descripcion, String Tipo) {
        this.producto_ID = producto_ID;
        this.pedido_ID = pedido_ID;
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.Descripcion = Descripcion;
        this.Tipo = Tipo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    public int getProducto_ID() {
        return producto_ID;
    }

    public void setProducto_ID(int producto_ID) {
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

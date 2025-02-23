package com.example.demo.entity;


public class Adicional {
    private int adicional_id;
    private int pedido_ID;
    private String Nombre;
    private int precio;

    public Adicional(int adicional_id, int pedido_ID, String Nombre, int precio) {
        this.adicional_id = adicional_id;
        this.pedido_ID = pedido_ID;
        this.Nombre = Nombre;
        this.precio = precio;
        
    }
    public int getAdicional_id() {
        return adicional_id;
    }

    public void setAdicional_id(int adicional_id) {
        this.adicional_id = adicional_id;
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
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }


}
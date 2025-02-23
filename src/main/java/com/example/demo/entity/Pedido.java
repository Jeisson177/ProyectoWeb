package com.example.demo.entity;


public class Pedido {
    private int pedido_id;
    private int operador_ID;
    private int domiciliario_ID;
    private boolean Estado;
    private String fecha;

    public Pedido(int pedido_id, int operador_ID, int domiciliario_ID, boolean Estado, String fecha) {
        this.pedido_id = pedido_id;
        this.operador_ID = operador_ID;
        this.domiciliario_ID = domiciliario_ID;
        this.Estado = Estado;
        this.fecha = fecha;
    }
    
}
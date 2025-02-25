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
    public int getPedido_id() {
        return pedido_id;
    }
    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
    }
    public int getOperador_ID() {
        return operador_ID;
    }
    public void setOperador_ID(int operador_ID) {
        this.operador_ID = operador_ID;
    }
    public int getDomiciliario_ID() {
        return domiciliario_ID;
    }
    public void setDomiciliario_ID(int domiciliario_ID) {
        this.domiciliario_ID = domiciliario_ID;
    }
    public boolean isEstado() {
        return Estado;
    }
    public void setEstado(boolean estado) {
        this.Estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
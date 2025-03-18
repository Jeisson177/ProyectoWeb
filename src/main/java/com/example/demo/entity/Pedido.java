package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
public class Pedido {

    @Positive(message = "El ID del operador debe ser un número positivo")
    private int operador_ID;

    @Positive(message = "El ID del domiciliario debe ser un número positivo")
    private int domiciliario_ID;

    
    private boolean estado;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "La fecha debe tener el formato YYYY-MM-DD")
    private String fecha;

    @Id
    @GeneratedValue
    private Long pedido_id;
    

    public Pedido(Long pedido_id, int operador_ID, int domiciliario_ID, boolean estado, String fecha) {
        this.pedido_id = pedido_id;
        this.operador_ID = operador_ID;
        this.domiciliario_ID = domiciliario_ID;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Pedido(int operador_ID, int domiciliario_ID, boolean estado, String fecha) {
        this.operador_ID = operador_ID;
        this.domiciliario_ID = domiciliario_ID;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Pedido() {}


    public Long getPedido_id() {
        return pedido_id;
    }
    public void setPedido_id(Long pedido_id) {
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
    public boolean isestado() {
        return estado;
    }
    public void setestado(boolean estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
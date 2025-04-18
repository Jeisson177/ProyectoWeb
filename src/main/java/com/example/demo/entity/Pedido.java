package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedido_id;
    
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;
    
    @Column(name = "operador_id", nullable = false)
    private Long operadorId; // Cambiado a Long
    
    @Column(name = "domiciliario_id")
    private Long domiciliarioId; // Cambiado a Long y nullable
    
    private boolean estado; // false: pendiente, true: completado
    
    private String direccionEnvio;
    
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime fecha;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<ItemCarrito> items = new ArrayList<>();
    
    // Constructor por defecto
    public Pedido() {
        this.fecha = LocalDateTime.now();
    }
    
    // Constructor completo
    public Pedido(Long clienteId, Long operadorId, Long domiciliarioId, boolean estado, String direccionEnvio) {
        this.clienteId = clienteId;
        this.operadorId = operadorId;
        this.domiciliarioId = domiciliarioId;
        this.estado = estado;
        this.direccionEnvio = direccionEnvio;
        this.fecha = LocalDateTime.now();
    }
    
    // Getters y Setters (actualizados)
    public Long getPedido_id() { return pedido_id; }
    public Long getClienteId() { return clienteId; }
    public Long getOperadorId() { return operadorId; }
    public Long getDomiciliarioId() { return domiciliarioId; }
    public boolean isEstado() { return estado; }
    public LocalDateTime getFecha() { return fecha; }
    public List<ItemCarrito> getItems() { return items; }
    public String getDireccionEnvio() { return direccionEnvio; }
    
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public void setOperadorId(Long operadorId) { this.operadorId = operadorId; }
    public void setDomiciliarioId(Long domiciliarioId) { this.domiciliarioId = domiciliarioId; }
    public void setEstado(boolean estado) { this.estado = estado; }
    public void setDireccionEnvio(String direccionEnvio) { this.direccionEnvio = direccionEnvio; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedido_id;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "operador_id", nullable = false)
    private Operador operador;
    
    @ManyToOne
    @JoinColumn(name = "domiciliario_id")
    private Domiciliario domiciliario;


    private String estado; // "RECIBIDO", "COCINANDO", "ENVIADO", "ENTREGADO"    
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
    public Pedido(Cliente clienteId, Operador operadorId, Domiciliario domiciliarioId, String estado, String direccionEnvio) {
        this.cliente = clienteId;
        this.operador = operadorId;
        this.domiciliario = domiciliarioId;
        this.estado = estado;
        this.direccionEnvio = direccionEnvio;
        this.fecha = LocalDateTime.now();
    }
    
    // Getters y Setters (actualizados)
    public Long getPedido_id() { return pedido_id; }
    public Cliente getCliente() { return cliente; }
    public Operador getOperadorId() { return operador; }
    public Domiciliario getDomiciliario() { return domiciliario; }
    public String isEstado() { return estado; }
    public LocalDateTime getFecha() { return fecha; }
    public List<ItemCarrito> getItems() { return items; }
    public String getDireccionEnvio() { return direccionEnvio; }
    
    public void setCliente(Cliente clienteId) { this.cliente = clienteId; }
    public void setOperador(Operador operadorId) { this.operador=operadorId; }
    public void setDomiciliario(Domiciliario domiciliarioId) { this.domiciliario=domiciliarioId; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setDireccionEnvio(String direccionEnvio) { this.direccionEnvio = direccionEnvio; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public void setItems(List<ItemCarrito> items) { this.items = items; }
}
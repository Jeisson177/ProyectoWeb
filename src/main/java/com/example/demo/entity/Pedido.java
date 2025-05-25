package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pedidos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonIgnoreProperties({"pedidos", "hibernateLazyInitializer", "handler"})
    private Cliente cliente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operador_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Operador operador;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domiciliario_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Domiciliario domiciliario;

    private String estado; // "RECIBIDO", "COCINANDO", "ENVIADO", "ENTREGADO"    
    private String direccionEnvio;
    
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime fecha;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> items = new ArrayList<>();
    
    // Constructor por defecto
    public Pedido() {
    }
    
    // Getters y Setters

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public Domiciliario getDomiciliario() {
        return domiciliario;
    }

    public void setDomiciliario(Domiciliario domiciliario) {
        this.domiciliario = domiciliario;
    }


    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }
}
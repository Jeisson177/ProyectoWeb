package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByOperador_ID(int operadorId);
    List<Pedido> findByDomiciliario_ID(int domiciliarioId);
    List<Pedido> findByEstado(boolean estado);
    List<Pedido> findByFecha(String fecha);

    public List<Pedido> findByDomiciliario_IDIsNull();

    public List<Pedido> findByClienteId(Long clienteId);

    
} 
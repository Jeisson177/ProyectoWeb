package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Domiciliario;
import com.example.demo.entity.Operador;
import com.example.demo.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    
    List<Pedido> findByEstado(boolean estado);
    List<Pedido> findByFecha(LocalDateTime fecha);

    List<Pedido> findByDomiciliarioIsNull();

    List<Pedido> findByCliente(Cliente cliente);

    List<Pedido> findByOperador(Operador operador);

    List<Pedido> findByDomiciliario(Domiciliario domiciliarioId);

    
} 
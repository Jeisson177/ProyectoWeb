package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Pedido;

public interface PedidoService {
    Pedido crearPedidoDesdeCarrito(Long carritoId, String direccionEnvio);
    Optional<Pedido> obtenerPedidoPorId(Long id);
    List<Pedido> obtenerPedidosPorCliente(Long clienteId);
    List<Pedido> obtenerTodosLosPedidos();
    void actualizarEstadoPedido(Long pedidoId, String nuevoEstado);
}
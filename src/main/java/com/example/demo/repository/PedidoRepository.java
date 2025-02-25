package com.example.demo.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Pedido;

@Repository
public class PedidoRepository {

    private Map<Integer, Pedido> pedidos = new HashMap<>();

    public PedidoRepository() {
        // Inicializamos la base de datos falsa con algunos pedidos de ejemplo
        pedidos.put(1, new Pedido(101, 1, 1, true, "2023-10-01"));
        pedidos.put(2, new Pedido(102, 2, 2, false, "2023-10-02"));
        pedidos.put(3, new Pedido(103, 3, 4, true, "2023-10-03"));
        pedidos.put(4, new Pedido(104, 4, 6, false, "2023-10-04"));
        pedidos.put(5, new Pedido(105, 6, 5, true, "2023-10-05"));
    }

    public Pedido getPedido(int pedido_id) {
        return pedidos.get(pedido_id);
    }

    public Collection<Pedido> getAllPedidos() {
        return pedidos.values();
    }

    // Método para agregar un nuevo pedido
    public void addPedido(Pedido pedido) {
        int newId = pedidos.size() + 1;
        pedido.setPedido_id(newId);
        pedidos.put(newId, pedido);
    }

    // Método para actualizar un pedido existente
    public void updatePedido(int pedido_id, Pedido pedido) {
        if (pedidos.containsKey(pedido_id)) {
            pedido.setPedido_id(pedido_id);
            pedidos.put(pedido_id, pedido);
        }
    }

    // Método para eliminar un pedido por su ID
    public void deletePedido(int pedido_id) {
        pedidos.remove(pedido_id);
    }

    // Método para obtener pedidos por estado (true: completado, false: pendiente)
    public Map<Integer, Pedido> getPedidosByEstado(boolean estado) {
        Map<Integer, Pedido> pedidosFiltrados = new HashMap<>();
        for (Map.Entry<Integer, Pedido> entry : pedidos.entrySet()) {
            if (entry.getValue().isEstado() == estado) {
                pedidosFiltrados.put(entry.getKey(), entry.getValue());
            }
        }
        return pedidosFiltrados;
    }

    // Método para obtener pedidos asignados a un domiciliario específico
    public Map<Integer, Pedido> getPedidosByDomiciliario(int domiciliario_ID) {
        Map<Integer, Pedido> pedidosDomiciliario = new HashMap<>();
        for (Map.Entry<Integer, Pedido> entry : pedidos.entrySet()) {
            if (entry.getValue().getDomiciliario_ID() == domiciliario_ID) {
                pedidosDomiciliario.put(entry.getKey(), entry.getValue());
            }
        }
        return pedidosDomiciliario;
    }

    // Método para obtener pedidos gestionados por un operador específico
    public Map<Integer, Pedido> getPedidosByOperador(int operador_ID) {
        Map<Integer, Pedido> pedidosOperador = new HashMap<>();
        for (Map.Entry<Integer, Pedido> entry : pedidos.entrySet()) {
            if (entry.getValue().getOperador_ID() == operador_ID) {
                pedidosOperador.put(entry.getKey(), entry.getValue());
            }
        }
        return pedidosOperador;
    }
}
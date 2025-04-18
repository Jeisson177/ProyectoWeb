package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final CarritoRepository carritoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public PedidoServiceImpl(CarritoRepository carritoRepository,
                           PedidoRepository pedidoRepository,
                           ProductoRepository productoRepository) {
        this.carritoRepository = carritoRepository;
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional
    public Pedido crearPedidoDesdeCarrito(Long carritoId, String direccionEnvio) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setClienteId(carrito.getClienteId());
        pedido.setDireccionEnvio(direccionEnvio);
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado("PENDIENTE");

        // Convertir items del carrito a items de pedido
        List<ItemPedido> itemsPedido = carrito.getItems().stream()
                .map(itemCarrito -> {
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setProducto(itemCarrito.getProducto());
                    itemPedido.setCantidad(itemCarrito.getCantidad());
                    itemPedido.setAdicionales(new ArrayList<>(itemCarrito.getAdicionales()));
                    return itemPedido;
                })
                .collect(Collectors.toList());

        pedido.setItems(itemsPedido);

        // Guardar pedido y limpiar carrito
        Pedido pedidoCreado = pedidoRepository.save(pedido);
        carrito.limpiarCarrito();
        carritoRepository.save(carrito);

        return pedidoCreado;
    }

    @Override
    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public List<Pedido> obtenerPedidosPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    @Override
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    @Transactional
    public void actualizarEstadoPedido(Long pedidoId, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setEstado(nuevoEstado);
        pedidoRepository.save(pedido);
    }
}
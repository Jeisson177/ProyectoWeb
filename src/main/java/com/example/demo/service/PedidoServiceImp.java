package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Carrito;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Domiciliario;
import com.example.demo.entity.ItemCarrito;
import com.example.demo.entity.Operador;
import com.example.demo.entity.Pedido;
import com.example.demo.repository.CarritoRepository;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.DomiciliarioRepository;
import com.example.demo.repository.ItemPedidoRepository;
import com.example.demo.repository.OperadorRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProductoRepository;

@Service
public class PedidoServiceImp implements PedidoService {
   
    @Autowired
    CarritoRepository carritoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    OperadorRepository operadorRepository;

    @Autowired
    DomiciliarioRepository domiciliarioRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public Pedido crearPedidoDesdeCarrito(Long carritoId, String direccionEnvio) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Cliente cliente = clienteRepository.findById(carrito.getClienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setOperador(null); // Se asignará posteriormente
        pedido.setDomiciliario(null); // Se asignará cuando se despache
        pedido.setEstado("RECIBIDO"); // Estado inicial
        pedido.setFecha(LocalDateTime.now());
        pedido.setDireccionEnvio(direccionEnvio);

        // Convertir ítems del carrito a pedido (sin implementar)
        convertirItemsCarritoAPedido(carrito, pedido);

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        // Limpiar el carrito después de crear el pedido
        carrito.limpiarCarrito();
        carritoRepository.save(carrito);

        return pedidoGuardado;
    }

    private void convertirItemsCarritoAPedido(Carrito carrito, Pedido pedido) {
        List<ItemCarrito> itemsCarrito = carrito.getItems();
        List<ItemCarrito> itemsParaPedido = new ArrayList<>();
        for (ItemCarrito item : itemsCarrito) {
            ItemCarrito nuevoItem = new ItemCarrito(
                /* carrito */ null,
                item.getProducto(),
                item.getCantidad(),
                new ArrayList<>(item.getAdicionales())
            );
            itemsParaPedido.add(nuevoItem);
        }
        pedido.setItems(itemsParaPedido);
    }

    @Override
    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public List<Pedido> obtenerPedidosPorCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return pedidoRepository.findByCliente(cliente);
    }

    @Override
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    @Transactional
    public void actualizarEstadoPedido(Long pedidoId, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        // Validar transición de estados
        if ("ENTREGADO".equals(nuevoEstado)) {
            pedido.setFecha(LocalDateTime.now());
            if (pedido.getDomiciliario() != null) {
                pedido.getDomiciliario().setdisponibilidad(true);
                domiciliarioRepository.save(pedido.getDomiciliario());
            }
        }
        
        pedido.setEstado(nuevoEstado);
        pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> obtenerPedidosByOperador(Long operadorId) {
        Operador operador = operadorRepository.findById(operadorId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return pedidoRepository.findByOperador(operador);
    }

    @Override
    public List<Pedido> obtenerPedidosByDomiciliario(Long domiciliarioId) {
        Domiciliario domiciliario = domiciliarioRepository.findById(domiciliarioId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return pedidoRepository.findByDomiciliario(domiciliario);
    }

    @Override
    public List<Pedido> obtenerPedidosPendientes() {
        return pedidoRepository.findByEstado(false);
    }

    @Override
    public Optional<Pedido> getPedidoById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    @Transactional
    public void asignarDomiciliario(Long pedidoId, Long domiciliarioId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        Domiciliario domiciliario = domiciliarioRepository.findById(domiciliarioId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        pedido.setDomiciliario(domiciliario);
        pedidoRepository.save(pedido);
    }
}
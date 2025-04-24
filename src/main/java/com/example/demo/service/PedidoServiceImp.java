package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Carrito;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Domiciliario;
import com.example.demo.entity.ItemPedido;
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

    @Autowired
    CarritoService carritoService;

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

        List<ItemPedido> items = carrito.getItems().stream()
            .map(itemCarrito -> new ItemPedido(
                pedido,
                itemCarrito.getProducto(),
                itemCarrito.getCantidad(),
                itemCarrito.getProducto().getPrecio()
            ))
            .collect(Collectors.toList());

        pedido.setItems(items);

        // Guardar el pedido
        Pedido savedPedido = pedidoRepository.save(pedido);

        // Limpiar el carrito
        carritoService.limpiarCarrito(carrito.getClienteId());

        return savedPedido;
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
        List<Pedido> pedidos = pedidoRepository.findAll();
        // Forzar carga del cliente si es LAZY
        pedidos.forEach(p -> {
            if (p.getCliente() != null) {
                p.getCliente().getNombre(); // solo con esto ya se carga
            }
        });
        return pedidos;
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
        return pedidoRepository.findByEstado("RECIBIDO");
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
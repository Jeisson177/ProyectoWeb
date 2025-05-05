package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class PedidoServiceTest {


    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DomiciliarioRepository domiciliarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CarritoService carritoService;

    
    @Test
     void testCrearPedidoDesdeCarrito() {
        // Preparar datos
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        clienteRepository.save(cliente);

        Producto producto = new Producto();
        producto.setNombre("Producto 1");
        producto.setPrecio(1000.0);
        productoRepository.save(producto);

        Carrito carrito = new Carrito();
        carrito.setClienteId(cliente.getId());
        carritoRepository.save(carrito);

        // Agregar producto al carrito
        carritoService.agregarProductoAlCarrito(cliente.getId(), producto.getId(), 2);

        // Actuar
        Pedido pedido = pedidoService.crearPedidoDesdeCarrito(carrito.getId(), "Calle Falsa 123");

        // Verificar
        Assertions.assertNotNull(pedido.getId());
        Assertions.assertEquals(1, pedido.getItems().size());
        Assertions.assertEquals("RECIBIDO", pedido.getEstado());
        Assertions.assertEquals("Calle Falsa 123", pedido.getDireccionEnvio());
    }

    @Test
    public void testObtenerPedidoPorId() {
        // Preparar datos
        Cliente cliente = new Cliente();
        cliente.setNombre("Ana");
        clienteRepository.save(cliente);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEstado("RECIBIDO");
        pedido.setFecha(LocalDateTime.now());
        pedido.setDireccionEnvio("Calle 123");
        pedidoRepository.save(pedido);

        // Actuar
        Optional<Pedido> pedidoObtenido = pedidoService.obtenerPedidoPorId(pedido.getId());

        // Verificar
        Assertions.assertTrue(pedidoObtenido.isPresent());
        Assertions.assertEquals("Ana", pedidoObtenido.get().getCliente().getNombre());
    }

    @Test
    public void testActualizarEstadoPedido() {
        // Preparar datos
        Cliente cliente = new Cliente();
        cliente.setNombre("Carlos");
        clienteRepository.save(cliente);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEstado("RECIBIDO");
        pedido.setFecha(LocalDateTime.now());
        pedido.setDireccionEnvio("Calle 456");
        pedidoRepository.save(pedido);

        // Actuar
        pedidoService.actualizarEstadoPedido(pedido.getId(), "EN CAMINO");

        // Verificar
        Pedido pedidoActualizado = pedidoRepository.findById(pedido.getId()).orElse(null);
        Assertions.assertNotNull(pedidoActualizado);
        Assertions.assertEquals("EN CAMINO", pedidoActualizado.getEstado());
    }

    @Test
    public void testAsignarDomiciliario() {
        // Preparar datos
        Cliente cliente = new Cliente();
        cliente.setNombre("Laura");
        clienteRepository.save(cliente);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEstado("RECIBIDO");
        pedido.setFecha(LocalDateTime.now());
        pedido.setDireccionEnvio("Calle 789");
        pedidoRepository.save(pedido);

        Domiciliario domiciliario = new Domiciliario();
        domiciliario.setNombre("Pedro");
        domiciliario.setDisponibilidad(true);
        domiciliarioRepository.save(domiciliario);

        // Actuar
        pedidoService.asignarDomiciliario(pedido.getId(), domiciliario.getId());

        // Verificar
        Pedido pedidoAsignado = pedidoRepository.findById(pedido.getId()).orElse(null);
        Assertions.assertNotNull(pedidoAsignado);
        Assertions.assertEquals("EN CAMINO", pedidoAsignado.getEstado());
        Assertions.assertEquals("Pedro", pedidoAsignado.getDomiciliario().getNombre());
    }

    @Test
    public void testFinalizarPedido() {
        // Preparar datos
        Cliente cliente = new Cliente();
        cliente.setNombre("Luis");
        clienteRepository.save(cliente);

        Domiciliario domiciliario = new Domiciliario();
        domiciliario.setNombre("Maria");
        domiciliario.setDisponibilidad(false);
        domiciliarioRepository.save(domiciliario);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDomiciliario(domiciliario);
        pedido.setEstado("EN CAMINO");
        pedido.setFecha(LocalDateTime.now());
        pedido.setDireccionEnvio("Calle 101");
        pedidoRepository.save(pedido);

        // Actuar
        pedidoService.finalizarPedido(pedido.getId());

        // Verificar
        Pedido pedidoFinalizado = pedidoRepository.findById(pedido.getId()).orElse(null);
        Assertions.assertNotNull(pedidoFinalizado);
        Assertions.assertEquals("ENTREGADO", pedidoFinalizado.getEstado());

        Domiciliario domiciliarioActualizado = domiciliarioRepository.findById(domiciliario.getId()).orElse(null);
        Assertions.assertNotNull(domiciliarioActualizado);
        Assertions.assertTrue(domiciliarioActualizado.getDisponibilidad());
    }
    
}

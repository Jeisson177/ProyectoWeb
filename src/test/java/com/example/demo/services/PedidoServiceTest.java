package com.example.demo.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Carrito;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Domiciliario;
import com.example.demo.entity.Pedido;
import com.example.demo.entity.Producto;
import com.example.demo.repository.CarritoRepository;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.DomiciliarioRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.CarritoService;
import com.example.demo.service.PedidoService;

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
        producto.setPrecio( 1000);
        productoRepository.save(producto);

        Carrito carrito = new Carrito();
        carrito.setClienteId(cliente.getId());
        carritoRepository.save(carrito);

        // Agregar producto al carrito
        carritoService.agregarProducto(cliente.getId(), producto.getProducto_id(), 2, null);

        // Actuar
        Pedido pedido = pedidoService.crearPedidoDesdeCarrito(carrito.getId(), "Calle Falsa 123");

        // Verificar
        Assertions.assertNotNull(pedido.getPedidoId());
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
        pedido.setDireccionEnvio("Calle 123");
        pedidoRepository.save(pedido);

        // Actuar
        Optional<Pedido> pedidoObtenido = pedidoService.obtenerPedidoPorId(pedido.getPedidoId());

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

        pedido.setDireccionEnvio("Calle 456");
        pedidoRepository.save(pedido);

        // Actuar
        pedidoService.actualizarEstadoPedido(pedido.getPedidoId(), "EN CAMINO");

        // Verificar
        Pedido pedidoActualizado = pedidoRepository.findById(pedido.getPedidoId()).orElse(null);
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
        pedido.setDireccionEnvio("Calle 789");
        pedidoRepository.save(pedido);

        Domiciliario domiciliario = new Domiciliario();
        domiciliario.setNombre("Pedro");
        domiciliario.setdisponibilidad(true);;
        domiciliarioRepository.save(domiciliario);

        // Actuar
        pedidoService.asignarDomiciliario(pedido.getPedidoId(), domiciliario.getId());

        // Verificar
        Pedido pedidoAsignado = pedidoRepository.findById(pedido.getPedidoId()).orElse(null);
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
        domiciliario.setdisponibilidad(false);
        domiciliarioRepository.save(domiciliario);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDomiciliario(domiciliario);
        pedido.setEstado("EN CAMINO");
        pedido.setDireccionEnvio("Calle 101");
        pedidoRepository.save(pedido);

        // Actuar
        pedidoService.finalizarPedido(pedido.getPedidoId());

        // Verificar
        Pedido pedidoFinalizado = pedidoRepository.findById(pedido.getPedidoId()).orElse(null);
        Assertions.assertNotNull(pedidoFinalizado);
        Assertions.assertEquals("ENTREGADO", pedidoFinalizado.getEstado());

        Domiciliario domiciliarioActualizado = domiciliarioRepository.findById(domiciliario.getId()).orElse(null);
        Assertions.assertNotNull(domiciliarioActualizado);
        Assertions.assertTrue(domiciliarioActualizado.isdisponibilidad());
    }
    
}

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

import jakarta.transaction.Transactional;

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
    // Crear cliente y guardar
    Cliente cliente = new Cliente();
    cliente.setNombre("Juan");
    cliente.setApellido("Pérez");
    cliente.setCorreo("juan@mail.com");
    cliente.setContrasena("1234abc12");
    cliente.setTelefono("123456789");
    cliente.setDireccion("Calle 123");
    Cliente clienteGuardado = clienteRepository.save(cliente);

    // Crear producto y guardar
    Producto producto = new Producto();
    producto.setNombre("Producto 1");
    producto.setPrecio(1000);
    producto.setDescripcion("Producto de prueba");
    producto.setCategoria("Alimentos");
    Producto productoGuardado = productoRepository.save(producto);

    // Crear carrito y guardar
    Carrito carrito = new Carrito();
    carrito.setClienteId(clienteGuardado.getId());
    Carrito carritoGuardado = carritoRepository.save(carrito);

    // Agregar producto al carrito
    carritoService.agregarProducto(clienteGuardado.getId(), productoGuardado.getProducto_id(), 2, null);

    // Crear pedido desde carrito
    Pedido pedido = pedidoService.crearPedidoDesdeCarrito(carritoGuardado.getId(), "Calle Falsa 123");

    // Verificaciones
    Assertions.assertNotNull(pedido.getPedidoId());
    Assertions.assertEquals(1, pedido.getItems().size());
    Assertions.assertEquals("RECIBIDO", pedido.getEstado());
    Assertions.assertEquals("Calle Falsa 123", pedido.getDireccionEnvio());
}


    @Test
    @Transactional
    public void testObtenerPedidoPorId() {
        // Preparar datos
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setCorreo("juan@mail.com");
        cliente.setContrasena("1234abc12");
        cliente.setTelefono("123456789");
        cliente.setDireccion("Calle 123");
        Cliente clienteGuardado = clienteRepository.save(cliente);

        Pedido pedido = new Pedido();
        pedido.setCliente(clienteGuardado); // asegúrate de usar el cliente guardado
        pedido.setEstado("RECIBIDO");
        pedido.setDireccionEnvio("Calle 123");
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        // Actuar
        Optional<Pedido> pedidoObtenido = pedidoService.obtenerPedidoPorId(pedidoGuardado.getPedidoId());

        // Verificar
        Assertions.assertTrue(pedidoObtenido.isPresent());
        Assertions.assertEquals("Juan", pedidoObtenido.get().getCliente().getNombre()); // corregido
    }


    @Test
    public void testActualizarEstadoPedido() {
        // Preparar datos
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setCorreo("juan@mail.com");
        cliente.setContrasena("1234abc12");
        cliente.setTelefono("123456789");
        cliente.setDireccion("Calle 123");
        Cliente clienteGuardado = clienteRepository.save(cliente);

        Pedido pedido = new Pedido();
        pedido.setCliente(clienteGuardado); // asegúrate de usar el cliente guardado
        pedido.setEstado("RECIBIDO");
        pedido.setDireccionEnvio("Calle 123");
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        // Actuar
        pedidoService.actualizarEstadoPedido(pedidoGuardado.getPedidoId(), "EN CAMINO");

        // Verificar
        Pedido pedidoActualizado = pedidoRepository.findById(pedidoGuardado.getPedidoId()).orElse(null);
        Assertions.assertNotNull(pedidoActualizado);
        Assertions.assertEquals("EN CAMINO", pedidoActualizado.getEstado());
    }

    @Test
    @Transactional
    public void testAsignarDomiciliario() {
        // Preparar datos
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setCorreo("juan@mail.com");
        cliente.setContrasena("1234abc12");
        cliente.setTelefono("123456789");
        cliente.setDireccion("Calle 123");
        Cliente clienteGuardado = clienteRepository.save(cliente);

        Pedido pedido = new Pedido();
        pedido.setCliente(clienteGuardado);
        pedido.setEstado("RECIBIDO");
        pedido.setDireccionEnvio("Calle 789");
        pedidoRepository.save(pedido);

        Domiciliario domiciliario = new Domiciliario();
        domiciliario.setNombre("Pedro");
        domiciliario.setdisponibilidad(true);
        domiciliario.setCelular("123456789");
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
    @Transactional
    public void testFinalizarPedido() {
        // Preparar datos
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setCorreo("juan@mail.com");
        cliente.setContrasena("1234abc12");
        cliente.setTelefono("123456789");
        cliente.setDireccion("Calle 123");
        Cliente clienteGuardado = clienteRepository.save(cliente);

        Domiciliario domiciliario = new Domiciliario();
        domiciliario.setNombre("Pedro");
        domiciliario.setdisponibilidad(true);
        domiciliario.setCelular("123456789");
        domiciliarioRepository.save(domiciliario);

        Pedido pedido = new Pedido();
        pedido.setCliente(clienteGuardado);
        pedido.setEstado("RECIBIDO");
        pedido.setDireccionEnvio("Calle 789");
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

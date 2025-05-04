package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Pedido;


@DataJpaTest
class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DomiciliarioRepository domiciliarioRepository;


    @Test
void testGuardarPedido() {
    // Crear cliente y guardarlo
    clienteRepository.save(new Cliente("Juan", "Pérez", "jperez@example.com", "1234abc12", "Calle 123", "555-1234"));
    Cliente clienteGuardado = clienteRepository.findByCorreo("jperez@example.com").get();

    // Crear pedido y asignar cliente y domiciliario
    Pedido pedido = new Pedido();
    pedido.setCliente(clienteGuardado);
    pedido.setDomiciliario(null);
    pedido.setEstado("RECIBIDO");

    Pedido guardado = pedidoRepository.save(pedido);

    assertNotNull(guardado.getPedidoId()); 
}

    @Test
    void testBuscarPorId() {
        clienteRepository.save(new Cliente("Juan", "Pérez", "jperez@example.com", "1234abc12", "Calle 123", "555-1234"));
        Cliente clienteGuardado = clienteRepository.findByCorreo("jperez@example.com").get();

        // Crear pedido y asignar cliente y domiciliario
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteGuardado);
        pedido.setDomiciliario(null);
        pedido.setEstado("RECIBIDO");

        Pedido guardado = pedidoRepository.save(pedido);

        Optional<Pedido> encontrado = pedidoRepository.findById(guardado.getPedidoId());
        assertTrue(encontrado.isPresent());
    }

    @Test
    void testActualizarPedido() {
        clienteRepository.save(new Cliente("Juan", "Pérez", "jperez@example.com", "1234abc12", "Calle 123", "555-1234"));
        Cliente clienteGuardado = clienteRepository.findByCorreo("jperez@example.com").get();

        // Crear pedido y asignar cliente y domiciliario
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteGuardado);
        pedido.setDomiciliario(null);
        pedido.setEstado("RECIBIDO");

        Pedido guardado = pedidoRepository.save(pedido);
        Pedido p = pedidoRepository.findById(guardado.getPedidoId()).get();
        p.setEstado("Enviado");
        Pedido actualizado = pedidoRepository.save(p);
        assertEquals("Enviado", actualizado.getEstado());
    }

    @Test
    void testEliminarPedido() {
        clienteRepository.save(new Cliente("Juan", "Pérez", "jperez@example.com", "1234abc12", "Calle 123", "555-1234"));
        Cliente clienteGuardado = clienteRepository.findByCorreo("jperez@example.com").get();

        // Crear pedido y asignar cliente y domiciliario
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteGuardado);
        pedido.setDomiciliario(null);
        pedido.setEstado("RECIBIDO");

        Pedido guardado = pedidoRepository.save(pedido);
        Pedido p = pedidoRepository.findById(guardado.getPedidoId()).get();
        pedidoRepository.deleteById(p.getPedidoId());
        assertFalse(pedidoRepository.findById(p.getPedidoId()).isPresent());
    }

    @Test
    void testListarPedidos() {
        clienteRepository.save(new Cliente("Juan", "Pérez", "jperez@example.com", "1234abc12", "Calle 123", "555-1234"));
        Cliente clienteGuardado = clienteRepository.findByCorreo("jperez@example.com").get();

        // Crear pedido y asignar cliente y domiciliario
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteGuardado);
        pedido.setDomiciliario(null);
        pedido.setEstado("RECIBIDO");

        Pedido guardado = pedidoRepository.save(pedido);
        List<Pedido> lista = pedidoRepository.findAll();
        assertFalse(lista.isEmpty());
    }

     @Test
    void testBuscarPedidosPorCliente() {
        // Crear y guardar cliente
        clienteRepository.save(new Cliente("María", "Gómez", "mgomez@example.com", "5678", "Avenida 456", "555-5678"));
        Cliente clienteGuardado = clienteRepository.findByCorreo("mgomez@example.com").get();

        // Crear pedidos asociados al cliente
        Pedido pedido1 = new Pedido();
        pedido1.setCliente(clienteGuardado);
        pedido1.setDomiciliario(null);
        pedido1.setEstado("RECIBIDO");

        Pedido pedido2 = new Pedido();
        pedido2.setCliente(clienteGuardado);
        pedido2.setDomiciliario(null);
        pedido2.setEstado("RECIBIDO");

        Pedido guardado=pedidoRepository.save(pedido1);
        Pedido guardado2=pedidoRepository.save(pedido2);

        // Ejecutar la consulta
        List<Pedido> pedidosDelCliente = pedidoRepository.findByCliente(clienteGuardado);

        // Verificar
        assertEquals(2, pedidosDelCliente.size());
        assertTrue(pedidosDelCliente.stream().allMatch(p -> p.getCliente().getId().equals(clienteGuardado.getId())));
    }

    @Test
    void testBuscarPorEstado() {
        List<Pedido> pedidos = pedidoRepository.findByEstado("RECIBIDO");
        assertNotNull(pedidos);
    }

    @Test
void testBuscarPedidosSinDomiciliario() {
    // Crear y guardar cliente
    clienteRepository.save(new Cliente("Carlos", "López", "clopez@example.com", "91011", "Boulevard 789", "555-9101"));

    Cliente clienteGuardado = clienteRepository.findByCorreo("clopez@example.com").get();

    // Crear pedido SIN domiciliario
    Pedido pedido = new Pedido();
    pedido.setCliente(clienteGuardado);
    pedido.setEstado("Pendiente");

    pedido.setDomiciliario(null); 
    pedidoRepository.save(pedido);

    // Ejecutar consulta
    List<Pedido> pedidosSinDomiciliario = pedidoRepository.findByDomiciliarioIsNull();

    // Verificar que el pedido está en los resultados
    assertFalse(pedidosSinDomiciliario.isEmpty());
}



}

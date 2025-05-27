package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.Cliente;

@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setDireccion("Calle falsa 123");
        cliente.setCorreo("juan@gmail.com");
        cliente.setContrasena("123456");
        cliente.setTelefono("3001234567");
        clienteRepository.save(cliente);
    }

    @Test
    public void testGuardarCliente() {
        Cliente nuevo = new Cliente();
        nuevo.setNombre("Ana");
        nuevo.setApellido("Perez");
        nuevo.setDireccion("Calle falsa 123");
        nuevo.setCorreo("ana@gmail.com");
        nuevo.setContrasena("abcdef");
        nuevo.setTelefono("3009876543");

        Cliente guardado = clienteRepository.save(nuevo);

        assertNotNull(guardado);
        assertNotNull(guardado.getId());
    }

    @Test
    public void testBuscarPorId() {
        Optional<Cliente> encontrado = clienteRepository.findById(cliente.getId());
        assertTrue(encontrado.isPresent());
        assertEquals("juan@gmail.com", encontrado.get().getCorreo());
    }

    @Test
    public void testBuscarPorCorreo() {
        Optional<Cliente> encontrado = clienteRepository.findByCorreo("juan@gmail.com");
        assertTrue(encontrado.isPresent());
        assertEquals("Juan", encontrado.get().getNombre());
    }

    @Test
    public void testBuscarPorCorreoYContrasena() {
        Optional<Cliente> encontrado = clienteRepository.findByCorreoAndContrasena("juan@gmail.com", "123456");
        assertTrue(encontrado.isPresent());
        assertEquals("Juan", encontrado.get().getNombre());
    }

    @Test
    public void testActualizarCliente() {
        cliente.setNombre("Juan Actualizado");
        Cliente actualizado = clienteRepository.save(cliente);

        assertEquals("Juan Actualizado", actualizado.getNombre());
    }

    @Test
    public void testEliminarCliente() {
        clienteRepository.deleteById(cliente.getId());
        Optional<Cliente> eliminado = clienteRepository.findById(cliente.getId());
        assertFalse(eliminado.isPresent());
    }
}

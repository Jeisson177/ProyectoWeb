package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cliente;
import com.example.demo.service.ClienteServiceImp;

@RestController
@RequestMapping("/clienteTabla")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteTablaController {

    @Autowired
    private ClienteServiceImp clienteService;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.obtenerTodosLosClientes();
    }

    @GetMapping("/{id}")
    public Cliente obtenerCliente(@PathVariable Long id) {
        return clienteService.getClienteById(id);
    }

    // Agregar nuevo cliente
    @PostMapping
    public void guardarCliente(@RequestBody Cliente cliente) {
        Cliente cliente=clienteService.guardarCliente(cliente);
    }

    // Actualizar cliente
    @PutMapping("/{id}")
    public void actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id); // Asegura que el ID esté correcto
        clienteService.actualizarCliente(cliente);
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
    }
}

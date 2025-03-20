package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public boolean autenticarCliente(String correo, String contrasena) {
        Optional<Cliente> cliente = clienteRepository.findByCorreoAndContrasena(correo, contrasena);
        return cliente.isPresent();
    }

    @Override
    public Optional<Cliente> obtenerClientePorCorreo(String correo) {
        return clienteRepository.findByCorreo(correo);
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null); 
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        if (!clienteRepository.findByCorreo(cliente.getCorreo()).isPresent()) {
            clienteRepository.save(cliente);
        }
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getId())) {
            clienteRepository.save(cliente);
        }
    }
}

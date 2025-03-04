package com.example.demo.service;



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
        return clienteRepository.findByCorreo(correo); // ✅ Asegúrate de tener este método en `ClienteRepository`
    }
}

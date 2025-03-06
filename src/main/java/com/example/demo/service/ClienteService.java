package com.example.demo.service;

import java.util.Collection;
import java.util.Optional;

import com.example.demo.entity.Cliente;


public interface ClienteService {
    public boolean autenticarCliente(String correo, String contrasena);
    public Optional<Cliente> obtenerClientePorCorreo(String correo);
    Collection<Cliente>obtenerTodosLosClientes();
}
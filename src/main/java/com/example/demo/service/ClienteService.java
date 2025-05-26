package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Cliente;


public interface ClienteService {
    public boolean autenticarCliente(String correo, String contrasena);
    public Cliente obtenerClientePorCorreo(String correo);
    
    List<Cliente>obtenerTodosLosClientes();
    Cliente getClienteById(Long id);
    Cliente guardarCliente(Cliente cliente);
    
    void eliminarCliente(Long id);
    void actualizarCliente(Cliente cliente);
}
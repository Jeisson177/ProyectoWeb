package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Administrador;

public interface AdministradorService {

    Optional<Administrador> obtenerPorCredenciales(String usuario, String contrasena);
    
} 

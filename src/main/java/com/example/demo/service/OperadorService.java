package com.example.demo.service;

import com.example.demo.entity.Operador;

import java.util.Optional;

public interface OperadorService {
    Optional<Operador> obtenerPorCredenciales(String usuario, String contrasena);
}

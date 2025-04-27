package com.example.demo.service;

import com.example.demo.entity.Operador;

import java.util.List;
import java.util.Optional;

public interface OperadorService {
    Optional<Operador> obtenerPorCredenciales(String usuario, String contrasena);
    List<Operador> obtenerTodos();
    Optional<Operador> obtenerPorId(Long id);
    Operador guardar(Operador operador);
    void eliminar(Long id);
}

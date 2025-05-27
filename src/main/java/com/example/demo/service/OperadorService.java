package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Operador;

public interface OperadorService {
    public Operador obtenerOperadorPorUsuario(String usuario);
    List<Operador> obtenerTodos();
    Optional<Operador> obtenerPorId(Long id);
    Operador guardar(Operador operador);
    void eliminar(Long id);
}

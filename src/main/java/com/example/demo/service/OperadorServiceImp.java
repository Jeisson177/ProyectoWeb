package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Operador;
import com.example.demo.repository.OperadorRepository;

@Service
public class OperadorServiceImp implements OperadorService {

    @Autowired
    private OperadorRepository operadorRepository;

    @Override
    public Operador obtenerOperadorPorUsuario(String usuario) {
        return operadorRepository.findByUsuario(usuario);
    }

    
    @Override
    public List<Operador> obtenerTodos() {
        return operadorRepository.findAll();
    }

    @Override
    public Optional<Operador> obtenerPorId(Long id) {
        return operadorRepository.findById(id);
    }

    @Override
    public Operador guardar(Operador operador) {
        return operadorRepository.save(operador);
    }

    @Override
    public void eliminar(Long id) {
        operadorRepository.deleteById(id);
    }
}

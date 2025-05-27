package com.example.demo.service;

import com.example.demo.entity.Operador;
import com.example.demo.repository.OperadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperadorServiceImp implements OperadorService {

    @Autowired
    private OperadorRepository operadorRepository;

    @Override
    public Optional<Operador> obtenerPorCredenciales(String usuario, String contrasena) {
        return operadorRepository.findByUsuarioAndContrasena(usuario, contrasena);
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

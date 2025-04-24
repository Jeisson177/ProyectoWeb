package com.example.demo.service;

import com.example.demo.entity.Operador;
import com.example.demo.repository.OperadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperadorServiceImp implements OperadorService {

    @Autowired
    private OperadorRepository operadorRepository;

    @Override
    public Optional<Operador> obtenerPorCredenciales(String usuario, String contrasena) {
        return operadorRepository.findByUsuarioAndContrasena(usuario, contrasena);
    }
}

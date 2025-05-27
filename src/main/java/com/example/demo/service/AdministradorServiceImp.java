package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Administrador;
import com.example.demo.repository.AdministradorRepository;

@Service
public class AdministradorServiceImp implements AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    public Optional<Administrador> obtenerPorCredenciales(String usuario, String contrasena) {
        return administradorRepository.findByUsuarioAndContrasena(usuario, contrasena);
    }
}

package com.example.demo.service;

import java.util.Collection;

import com.example.demo.entity.Adicional;


public interface AdicionalService {
    Collection<Adicional> getAllAdicionales();
    Adicional getAdicionalById(Long id);
    void guardarAdicional(Adicional adicional);
    void actualizarAdicional(Adicional adicional);
    void eliminarAdicional(Long id);
    
}

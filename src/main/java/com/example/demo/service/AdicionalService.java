package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Adicional;


public interface AdicionalService {
    List<Adicional> getAllAdicionales();
    Adicional getAdicionalById(Long id);
    Adicional guardarAdicional(Adicional adicional);
    void actualizarAdicional(Adicional adicional);
    void eliminarAdicional(Long id);
    List<Adicional> getAdicionalesByIds(List<Long> ids);
}

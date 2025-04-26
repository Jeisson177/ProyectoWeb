package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Domiciliario;



public interface DomiciliarioService {
    List<Domiciliario> getAllDomiciliarios();
    List<Domiciliario> getDomiciliariosByDisponibilidad(boolean disponibilidad );
    Domiciliario createDomiciliario(Domiciliario domiciliario);
    Domiciliario updateDomiciliario(Long id, Domiciliario domiciliario);
    void deleteDomiciliario(Long id);
    Domiciliario getDomiciliarioById(Long id);

}
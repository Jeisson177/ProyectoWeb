package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Domiciliario;
import com.example.demo.repository.DomiciliarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DomicliarioServiceImp implements DomiciliarioService {

    @Autowired
    private DomiciliarioRepository domiciliarioRepository;

    @Override
    public List<Domiciliario> getAllDomiciliarios() {
        return domiciliarioRepository.findAll();
    }

    @Override
    public List<Domiciliario> getDomiciliariosByDisponibilidad(boolean disponibilidad) {
        return domiciliarioRepository.findAll().stream()
            .filter(d -> d.isdisponibilidad() == disponibilidad)
            .toList();
    }

    @Override
    public Domiciliario createDomiciliario(Domiciliario domiciliario) {
        return domiciliarioRepository.save(domiciliario);
    }

    @Override
    public Domiciliario updateDomiciliario(Long id, Domiciliario domiciliario) {
        Domiciliario existing = domiciliarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Domiciliario no encontrado con ID: " + id));

        existing.setNombre(domiciliario.getNombre());
        existing.setCelular(domiciliario.getCelular());
        existing.setdisponibilidad(domiciliario.isdisponibilidad());
        
        return domiciliarioRepository.save(existing);
    }

    @Override
    public void deleteDomiciliario(Long id) {
        domiciliarioRepository.deleteById(id);
    }

    @Override
    public Domiciliario getDomiciliarioById(Long id) {
        return domiciliarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Domiciliario no encontrado con ID: " + id));
    }
}
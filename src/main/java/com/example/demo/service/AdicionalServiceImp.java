package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Adicional;
import com.example.demo.repository.AdicionalRepository;

@Service
public class AdicionalServiceImp implements AdicionalService{
    @Autowired
    private AdicionalRepository adicionalRepository;

    @Override
    public List<Adicional> getAllAdicionales() {
        System.out.println(adicionalRepository.findAll().size());
        return adicionalRepository.findAll();
    }

    @Override
    public Adicional getAdicionalById(Long id) {
        return adicionalRepository.findById(id).orElse(null);
    }
    
    @Override
    public void guardarAdicional(Adicional adicional) {
        adicionalRepository.save(adicional);
    }
    
    @Override
    public void actualizarAdicional(Adicional adicional) {
        if (adicionalRepository.existsById(adicional.getAdicional_id())) {
            adicionalRepository.save(adicional);
        }
    }
    
    @Override
    public void eliminarAdicional(Long id) {
        adicionalRepository.deleteById(id);
    }
}

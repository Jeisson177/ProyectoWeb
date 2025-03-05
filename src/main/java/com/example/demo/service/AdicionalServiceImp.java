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

    public List<Adicional> getAllAdicionales() {
        return adicionalRepository.findAll();
    }

    public Adicional getAdicionalById(Long id) {
        return adicionalRepository.findById(id).orElse(null);
    }
    
    public Adicional guardarAdicional(Adicional adicional) {
        return adicionalRepository.save(adicional);
    }
    
    public Adicional actualizarAdicional(Adicional adicional) {
        if (adicionalRepository.existsById(adicional.getAdicional_id())) {
            return adicionalRepository.save(adicional);
        }
    }
    
    public void eliminarAdicional(Long id) {
        adicionalRepository.deleteById(id);
    }
}

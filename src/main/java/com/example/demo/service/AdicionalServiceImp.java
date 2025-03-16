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
        // Obtén el adicional existente de la base de datos
        Adicional adicionalExistente = adicionalRepository.findById(adicional.getAdicional_id()).orElse(null);
        if (adicionalExistente != null) {
            // Conserva el producto_id del adicional existente
            adicional.setProductos(adicionalExistente.getProductos());
            // Actualiza los demás campos
            adicionalExistente.setNombre(adicional.getNombre());
            adicionalExistente.setPrecio(adicional.getPrecio());
            // Guarda el adicional actualizado
            adicionalRepository.save(adicionalExistente);
        }
    }
    
    @Override
    public void eliminarAdicional(Long id) {
        adicionalRepository.deleteById(id);
    }

    @Override
    public List<Adicional> getAdicionalesByIds(List<Long> ids) {
        return adicionalRepository.findAllById(ids);
    }
}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Adicional;
import com.example.demo.service.AdicionalServiceImp;

@RestController
@RequestMapping("/adicionales")
@CrossOrigin(origins = "http://localhost:4200") // Cambia si usas otro puerto o deployas el frontend
public class adicionalesTablaController {

    @Autowired
    AdicionalServiceImp adicionalService;

    // Obtener todos los adicionales
    @GetMapping
    public List<Adicional> getAllAdicionales() {
        return adicionalService.getAllAdicionales();
    }

    // Obtener un adicional por ID
    @GetMapping("/{id}")
    public Adicional getAdicionalById(@PathVariable Long id) {
        return adicionalService.getAdicionalById(id);
    }

    // Crear un nuevo adicional
    @PostMapping
    public ResponseEntity<Adicional> createAdicional(@RequestBody Adicional adicional) {
        
        Adicional nuevo = adicionalService.guardarAdicional(adicional);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }


    // Actualizar un adicional existente
    @PutMapping("/{id}")
    public void updateAdicional(@PathVariable Long id, @RequestBody Adicional adicional) {
        adicional.setAdicional_id(id); 
        adicionalService.actualizarAdicional(adicional);
    }

    // Eliminar un adicional
    @DeleteMapping("/{id}")
    public void deleteAdicional(@PathVariable Long id) {
        adicionalService.eliminarAdicional(id);
    }
}

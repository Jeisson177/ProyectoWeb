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
public ResponseEntity<Adicional> updateAdicional(@PathVariable Long id, @RequestBody Adicional adicional) {
    // Buscar el adicional por ID
    Adicional adicionalExistente = adicionalService.getAdicionalById(id);
    
    if (adicionalExistente != null) {
        // Actualizamos las propiedades (nombre, cantidad, precio, etc.)
        adicionalExistente.setNombre(adicional.getNombre());
        adicionalExistente.setCantidad(adicional.getCantidad());
        adicionalExistente.setPrecio(adicional.getPrecio());
        
        // Guardamos la entidad actualizada
        adicionalService.actualizarAdicional(adicionalExistente);
        
        // Retornamos la respuesta con el adicional actualizado
        return ResponseEntity.status(HttpStatus.OK).body(adicionalExistente);
    }
    
    // En caso de que no se haya encontrado el adicional
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}

// "Eliminar" un adicional (marcar como no disponible)
@PutMapping("/eliminar/{id}")
public ResponseEntity<Adicional> eliminarAdicional(@PathVariable Long id) {
    // Buscar el adicional por ID
    Adicional adicionalExistente = adicionalService.getAdicionalById(id);
    
    if (adicionalExistente != null) {
        // Marcamos el adicional como no disponible
        adicionalExistente.setTemporada(false); 
        
        // Guardamos la entidad con el cambio de disponibilidad
        adicionalService.actualizarAdicional(adicionalExistente); 
        
        // Retornamos la respuesta con el adicional actualizado
        return ResponseEntity.status(HttpStatus.OK).body(adicionalExistente);
    }
    
    // En caso de que no se haya encontrado el adicional
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}

}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Domiciliario;
import com.example.demo.service.DomiciliarioService;


@RestController
@RequestMapping("/domiciliario")
@CrossOrigin(origins = "http://localhost:4200")
public class DomiciliarioController {

    @Autowired
    private DomiciliarioService  domiciliarioService;

    @GetMapping
    public List<Domiciliario> getAllDomiciliarios() {
        return domiciliarioService.getAllDomiciliarios();
    }

    @GetMapping("/{id}")
    public Domiciliario getDomiciliarioById(@PathVariable Long id) {
        return domiciliarioService.getDomiciliarioById(id);
    }


    @PostMapping
    public Domiciliario createDomiciliario(@RequestBody Domiciliario domiciliario) {
        return domiciliarioService.createDomiciliario(domiciliario);
    }

    @PutMapping("/{id}")
    public Domiciliario updateDomiciliario(@PathVariable Long id, @RequestBody Domiciliario domiciliario) {
        return domiciliarioService.updateDomiciliario(id, domiciliario);
    }

    @DeleteMapping("/{id}")
    public void deleteDomiciliario(@PathVariable Long id) {
        domiciliarioService.deleteDomiciliario(id);
    }

    // Método adicional que necesitamos para la lista de domiciliarios disponibles
    @GetMapping("/disponibles")
    public List<Domiciliario> getDomiciliariosDisponibles() {
        return domiciliarioService.getDomiciliariosByDisponibilidad(true);
    }


}
package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.ClienteDTO;
import com.example.demo.DTOs.OperadorDTO;
import com.example.demo.DTOs.OperadorMapper;
import com.example.demo.entity.Operador;
import com.example.demo.service.OperadorServiceImp;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/Operador")
@CrossOrigin(origins = "http://localhost:4200")
public class OperadorController {
    
    @Autowired
    private OperadorServiceImp operadorService;

    @PostMapping("/loginOperador")
    public ResponseEntity login(@RequestBody Operador operador) {

        operador = operadorService.obtenerOperadorPorUsuario(operador.getUsuario());

        if (operador == null) {
            return new ResponseEntity<String>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }

        OperadorDTO operadorDTO = OperadorMapper.INSTANCE.convert(operador);

        if (operador.getcontrasena().equals(operador.getcontrasena())) {
            return new ResponseEntity<OperadorDTO>(operadorDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<OperadorDTO>(operadorDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Operador> crearOperador(@RequestBody Operador operador) {
        Operador nuevoOperador = operadorService.guardar(operador);
        return new ResponseEntity<>(nuevoOperador, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Operador>> listarOperadores() {
        List<Operador> operadores = operadorService.obtenerTodos();
        return ResponseEntity.ok(operadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operador> obtenerOperador(@PathVariable Long id) {
        Optional<Operador> operador = operadorService.obtenerPorId(id);
        return operador.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Operador> actualizarOperador(@PathVariable Long id, @RequestBody Operador operadorDetalles) {
        Optional<Operador> operadorOptional = operadorService.obtenerPorId(id);

        if (operadorOptional.isPresent()) {
            Operador operador = operadorOptional.get();
            operador.setNombre(operadorDetalles.getNombre());
            operador.setUsuario(operadorDetalles.getUsuario());
            operador.setcontrasena(operadorDetalles.getcontrasena());
            operador.setDisponible(operadorDetalles.isDisponible());

            operadorService.guardar(operador);
            return ResponseEntity.ok(operador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarOperador(@PathVariable Long id) {
        operadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
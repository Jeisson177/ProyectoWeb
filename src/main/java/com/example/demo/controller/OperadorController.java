package com.example.demo.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String usuario = credentials.get("usuario");
        String contrasena = credentials.get("contrasena");

        Optional<Operador> operador = operadorService.obtenerPorCredenciales(usuario, contrasena);

        if (operador.isPresent()) {
            return ResponseEntity.ok().body(Map.of(
                "mensaje", "Login exitoso",
                "operador", operador.get()
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Credenciales inválidas"));
        }
    }
}

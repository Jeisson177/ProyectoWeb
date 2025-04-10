package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cliente;
import com.example.demo.service.ClienteService;

@RestController
@RequestMapping("/registro")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistroController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/registrar")
    public Map<String, Object> registrarCliente(@RequestBody Cliente cliente) {
        clienteService.guardarCliente(cliente);
        return Map.of(
            "success", true,
            "message", "Registro exitoso"
        );
    }
}


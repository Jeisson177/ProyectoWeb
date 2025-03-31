package com.example.demo.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cliente;
import com.example.demo.service.ClienteService;
@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {


    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public Map<String, Object> loginCliente(@RequestBody Map<String, String> credentials) {
        String correo = credentials.get("correo");
        String contrasena = credentials.get("contrasena");

        boolean autenticado = clienteService.autenticarCliente(correo, contrasena);

        if (autenticado) {
            Optional<Cliente> cliente = clienteService.obtenerClientePorCorreo(correo);
            return Map.of(
                "success", true,
                "cliente", cliente.orElse(null)
            );
        } else {
            return Map.of(
                "success", false,
                "message", "Correo o contraseña incorrectos"
            );
        }
    }
    

}



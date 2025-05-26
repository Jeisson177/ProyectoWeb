package com.example.demo.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.ClienteDTO;
import com.example.demo.DTOs.ClienteMapper;
import com.example.demo.entity.Cliente;
import com.example.demo.service.ClienteService;
@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {


    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity loginCliente(@RequestBody Cliente cliente) {

        cliente = clienteService.obtenerClientePorCorreo(cliente.getCorreo());

        if (cliente == null) {
            return new ResponseEntity<String>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }

        ClienteDTO clienteDTO = ClienteMapper.INSTANCE.convert(cliente);
        if (cliente.getContrasena().equals(cliente.getContrasena())) {
            return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.BAD_REQUEST);
        }
    }
    

}



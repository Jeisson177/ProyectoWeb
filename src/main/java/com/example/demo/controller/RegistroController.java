package com.example.demo.controller;


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
@RequestMapping("/registro")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistroController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/registrar")
    public ResponseEntity<ClienteDTO> registrarCliente(@RequestBody Cliente cliente) {
        Cliente newCliente = clienteService.guardarCliente(cliente);
        ClienteDTO clienteDTO = ClienteMapper.INSTANCE.convert(newCliente);

        if(newCliente == null) {
            return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.CREATED);
        
    }
}


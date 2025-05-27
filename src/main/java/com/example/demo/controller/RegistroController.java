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
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.CustomUserDetailService;
import com.example.demo.service.ClienteService;

@RestController
@RequestMapping("/registro")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistroController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CustomUserDetailService customUserDetailService;


    RegistroController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/registrar")
    public ResponseEntity registrarCliente(@RequestBody Cliente cliente) {
        
        if(userRepository.existsByUsername(cliente.getCorreo())) {
            return new ResponseEntity<String>("Este usuario ya existe", HttpStatus.BAD_REQUEST);       
        }

        UserEntity userEntity= customUserDetailService.ClienteToUser(cliente);
        cliente.setUser(userEntity);
        Cliente newCliente = clienteService.guardarCliente(cliente);

         if(newCliente == null) {
            return new ResponseEntity<Cliente>(newCliente, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Cliente>(newCliente, HttpStatus.CREATED);
    }
}


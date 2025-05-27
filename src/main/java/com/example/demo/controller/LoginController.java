package com.example.demo.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Cliente;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.CustomUserDetailService;
import com.example.demo.service.ClienteService;
@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {


    @Autowired
    private ClienteService clienteService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity loginCliente(@RequestBody Cliente cliente) {


        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(cliente.getCorreo(), 123)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<String>("Login exitoso", HttpStatus.OK);

       
    }
    

}



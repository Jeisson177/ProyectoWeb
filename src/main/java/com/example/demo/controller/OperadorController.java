package com.example.demo.controller;

import java.util.List;
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
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Operador;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.CustomUserDetailService;
import com.example.demo.security.JWTGenerator;
import com.example.demo.service.OperadorServiceImp;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/Operador")
@CrossOrigin(origins = "http://localhost:4200")
public class OperadorController {
    
    @Autowired
    private OperadorServiceImp operadorService;

     @Autowired
    private UserRepository userRepository;

     @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    AuthenticationManager authenticationManager;

     @Autowired
    JWTGenerator jwtGenerator;



    @PostMapping("/loginOperador")
    public ResponseEntity login(@RequestBody Operador operador) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(operador.getUsuario(), operador.getcontrasena())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity crearOperador(@RequestBody Operador operador) {

        if(userRepository.existsByUsername(operador.getUsuario())) {
            return new ResponseEntity<String>("Este usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity= customUserDetailService.OperadorToUser(operador);
        operador.setUser(userEntity);
        Operador operadorDB = operadorService.guardar(operador);
        OperadorDTO newOperador = OperadorMapper.INSTANCE.convert(operadorDB);

         if(newOperador == null) {
            return new ResponseEntity<OperadorDTO>(newOperador, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<OperadorDTO>(newOperador, HttpStatus.CREATED);
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

    @GetMapping("/details")
    public ResponseEntity<Operador> buscarOperador () {
        Operador operador = operadorService.obtenerOperadorPorUsuario(
             SecurityContextHolder.getContext().getAuthentication().getName());

        if (operador == null) {
            return new ResponseEntity<Operador>(operador, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Operador>(operador, HttpStatus.OK);
    }
}
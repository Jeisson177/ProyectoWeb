package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Adicional;
import com.example.demo.entity.Administrador;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Producto;
import com.example.demo.service.AdicionalService;
import com.example.demo.service.AdministradorService;
import com.example.demo.service.ClienteService;
import com.example.demo.service.ProductoService;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.DTOs.AdminDTO;
import com.example.demo.DTOs.AdminMapper;
import com.example.demo.DTOs.ClienteDTO;


@RestController
@RequestMapping("/Admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
    @Autowired
    ClienteService clienteService;
    @Autowired
    ProductoService productoService;

    @Autowired
    AdicionalService adicionalService;

    @Autowired
    private AdministradorService administradorService;

    @PostMapping("/loginAdmin")
    public ResponseEntity login(@RequestBody Administrador administrador) {
        
        administrador = administradorService.obtenerAdminPorUsuario(administrador.getUsuario());

        if (administrador == null) {
            return new ResponseEntity<String>("No estas registrado como admin", HttpStatus.NOT_FOUND);
        }

        AdminDTO adminDTO = AdminMapper.INSTANCE.convert(administrador);

        if (administrador.getContrasena().equals(administrador.getContrasena())) {
            return new ResponseEntity<AdminDTO>(adminDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<AdminDTO>(adminDTO, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/usuarios")
    public List<Cliente> getUsuarios() {
        return clienteService.obtenerTodosLosClientes();
    }

    @GetMapping("/productos")
    public List<Producto> getProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/adicionales")
    public List<Adicional> getAdicionales() {
        return adicionalService.getAllAdicionales();
    }
}

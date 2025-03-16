package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Cliente;
import com.example.demo.service.ClienteService;



@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/homeCliente")
    public String mostrarPerfil(@RequestParam("correo") String correo, Model model) {
        Optional<Cliente> cliente = clienteService.obtenerClientePorCorreo(correo);

        if (cliente.isPresent()) {
            List<Map<String, String>> promociones = List.of(
            Map.of("titulo", "¡Pasta marinada 2x1!", "descripcion", "Spaguetti en salsa marinera con camarones."),
            Map.of("titulo", "¡Pizza 2x1 todos los días!", "descripcion", "Pizza con queso cottage, tomates y finas hierbas."),
            Map.of("titulo", "¡Pizza Buratta con 40% de descuento!", "descripcion", "Pizza de jamón serrano con queso fundido."),
            Map.of("titulo", "¡60% de descuento para cumpleañeros!", "descripcion", "Spaguetti a la bolognesa relleno de queso parmesano.")
        );

        model.addAttribute("promociones", promociones);
            model.addAttribute("cliente", cliente.get()); 
            return "homeCliente";
        } else {
            model.addAttribute("error", "Cliente no encontrado");
            return "error"; // Redirigir a una página de error si no se encuentra el cliente
        }
    }
}
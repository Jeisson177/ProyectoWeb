package com.example.demo.controller;

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
            model.addAttribute("cliente", cliente.get()); // ✅ Envía el objeto cliente a la vista
            return "homeCliente";
        } else {
            model.addAttribute("error", "Cliente no encontrado");
            return "error"; // ✅ Puedes redirigir a una página de error si no se encuentra el cliente
        }
    }
}
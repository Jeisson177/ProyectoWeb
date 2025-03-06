package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ClienteServiceImp;

@Controller
@RequestMapping("/clientes")
public class ClienteTablaController {

    @Autowired
    private ClienteServiceImp clienteService;

    @GetMapping("/tb")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        return "menuTablaClientes";
    }
}
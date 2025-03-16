package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.ClienteService;





@Controller
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private ClienteService ClienteService;

    @RequestMapping("/Cliente")
    public String loginCliente() {
        return "login";
    }
    
    @PostMapping
    public String loginClientePost(@RequestParam("correo") String correo, @RequestParam("contrasena") String contrasena, Model model) {
        System.out.println("Correo recibido: " + correo);
        System.out.println("Contraseña recibida: " + contrasena);
        if (ClienteService.autenticarCliente(correo, contrasena)) {
            return "redirect:/homeCliente?correo=" + correo; 
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login"; 
        }
    }

    // Mostrar pagina de registro
    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }

    

}



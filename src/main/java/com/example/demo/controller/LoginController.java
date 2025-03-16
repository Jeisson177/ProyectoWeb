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

    @GetMapping
    public String loginCliente() {
        return "login";
    }
    
    @PostMapping
    public String loginClientePost(@RequestParam("correo") String correo, @RequestParam("contrasena") String contrasena, Model model) {
        if (ClienteService.autenticarCliente(correo, contrasena)) {
            return "redirect:/homeCliente?correo=" + correo; 
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login"; 
        }
    }
<<<<<<< HEAD
=======

    // Mostrar pagina de registro
    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }

>>>>>>> 053ea00e74bd0d1b5f1f5e34baf7afd45f3598ff
    

}



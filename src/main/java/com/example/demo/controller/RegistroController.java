package com.example.demo.controller;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Cliente;
import com.example.demo.service.ClienteService;
=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
>>>>>>> 053ea00e74bd0d1b5f1f5e34baf7afd45f3598ff

@Controller
@RequestMapping("/registro")
public class RegistroController {

<<<<<<< HEAD
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String mostrarRegistro(Model model) {
        model.addAttribute("cliente", new Cliente()); // Se envía un objeto vacío
        return "registro";
    }

    @PostMapping
    public String registrarCliente(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        clienteService.guardarCliente(cliente);
        redirectAttributes.addFlashAttribute("mensaje", "Registro exitoso");
        return "redirect:/login"; // Redirige al login después de registrarse
    }
}

=======
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    
}
>>>>>>> 053ea00e74bd0d1b5f1f5e34baf7afd45f3598ff

package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteService.getClienteById(id);
        if (cliente == null) {
            return "redirect:/error404"; // Puedes cambiarlo a una página de error personalizada
        }
        model.addAttribute("cliente", cliente);
        return "editarPerfil"; // Nombre de la vista (archivo HTML o Thymeleaf)
    }

    // Actualizar cliente
    @PostMapping("/actualizar")
    public String actualizarCliente(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        clienteService.actualizarCliente(cliente);
        redirectAttributes.addFlashAttribute("mensaje", "Perfil actualizado correctamente");
        return "redirect:/perfil/editar/" + cliente.getId();
    }

    @GetMapping("/pedidos")
    public String mostrarPedidos(@RequestParam("correo") String correo, Model model) {
        Optional<Cliente> clienteOpt = clienteService.obtenerClientePorCorreo(correo);

        if (clienteOpt.isEmpty()) {
            return "redirect:/error";
        }

        model.addAttribute("cliente", clienteOpt.get());
        return "pedidos"; 
    }

    @GetMapping("/historialPedidos")
    public String mostrarHistorial(@RequestParam("correo") String correo, Model model) {
        Optional<Cliente> clienteOpt = clienteService.obtenerClientePorCorreo(correo);

        if (clienteOpt.isEmpty()) {
            return "redirect:/error";
        }

        model.addAttribute("cliente", clienteOpt.get());
        return "historialPedidos"; 
    }



}
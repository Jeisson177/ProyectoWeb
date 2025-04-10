package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cliente;
import com.example.demo.service.ClienteService;



@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/perfil")
    public Object obtenerPerfil(@RequestParam("correo") String correo) {
        Optional<Cliente> cliente = clienteService.obtenerClientePorCorreo(correo);

        if (cliente.isPresent()) {
            List<Map<String, String>> promociones = List.of(
                Map.of("titulo", "¡Pasta marinada 2x1!", "descripcion", "Spaguetti en salsa marinera con camarones."),
                Map.of("titulo", "¡Pizza 2x1 todos los días!", "descripcion", "Pizza con queso cottage, tomates y finas hierbas."),
                Map.of("titulo", "¡Pizza Buratta con 40% de descuento!", "descripcion", "Pizza de jamón serrano con queso fundido."),
                Map.of("titulo", "¡60% de descuento para cumpleañeros!", "descripcion", "Spaguetti a la bolognesa relleno de queso parmesano.")
            );

            return Map.of(
                "cliente", cliente.get(),
                "promociones", promociones
            );
        } else {
            return Map.of("error", "Cliente no encontrado");
        }
    }

    @GetMapping("/editar")
    public Object obtenerClienteParaEditar(@RequestParam("correo") String correo) {
        return clienteService.obtenerClientePorCorreo(correo);
    }

    @PutMapping("/actualizarCliente")
    public void guardarCliente(@ModelAttribute Cliente cliente) {
        clienteService.actualizarCliente(cliente); // Llama al servicio para actualizar el cliente
    }

    @DeleteMapping("/eliminarCuenta/{id}")
    public void  eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
    }

    @GetMapping("/pedidos")
    public Object obtenerPedidos(@RequestParam("correo") String correo) {
        return clienteService.obtenerClientePorCorreo(correo);
    }

    @GetMapping("/historial")
    public Object obtenerHistorialPedidos(@RequestParam("correo") String correo) {
        return clienteService.obtenerClientePorCorreo(correo);
    }

}
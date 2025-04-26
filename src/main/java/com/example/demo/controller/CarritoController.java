package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Carrito;
import com.example.demo.entity.ItemPedido;
import com.example.demo.entity.Pedido;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.service.CarritoService;
import com.example.demo.service.ClienteService;

@RestController
@RequestMapping("/carritos")
@CrossOrigin(origins = "http://localhost:4200")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

   @Autowired
   private ClienteService clienteService;

   @Autowired
   PedidoRepository pedidoRepository;


   @PostMapping
   public ResponseEntity<Map<String, Long>> agregarProductoAlCarrito(
           @RequestParam Long clienteId,
           @RequestParam Long productoId,
           @RequestParam int cantidad,
           @RequestParam String direccionEnvio,  // Recibir la dirección desde el frontend
           @RequestBody(required = false) List<Long> adicionalesIds) {
   
       if (adicionalesIds == null) {
           adicionalesIds = new ArrayList<>();  // Esto asegura que no haya un valor nulo al procesar
       }
       
       // Primero, agregamos el producto al carrito
       Carrito carrito = carritoService.agregarProducto(clienteId, productoId, cantidad, adicionalesIds);
   
       // Ahora, creamos el pedido usando la dirección proporcionada desde el frontend
       Pedido pedido = new Pedido();
       pedido.setCliente(clienteService.getClienteById(clienteId));  // Cliente asociado al carrito
       pedido.setOperador(null); // Operador no asignado aún
       pedido.setDomiciliario(null); // Domiciliario no asignado aún
       pedido.setEstado("RECIBIDO"); // Estado inicial
       pedido.setFecha(LocalDateTime.now()); // Fecha actual
       pedido.setDireccionEnvio(direccionEnvio);  // Asignamos la dirección recibida desde el frontend
   
       // Creando los items del pedido desde el carrito
       List<ItemPedido> itemsPedido = carrito.getItems().stream()
           .map(itemCarrito -> {
               ItemPedido itemPedido = new ItemPedido();
               itemPedido.setPedido(pedido);
               itemPedido.setProducto(itemCarrito.getProducto());
               itemPedido.setCantidad(itemCarrito.getCantidad());
               itemPedido.setPrecioUnitario(itemCarrito.getProducto().getPrecio());
               return itemPedido;
           })
           .collect(Collectors.toList());
       
       pedido.setItems(itemsPedido);
   
       // Guardar el pedido
       pedidoRepository.save(pedido);
   
       // Devolver la respuesta con el ID del carrito y el ID del pedido creado
       Map<String, Long> response = new HashMap<>();
       response.put("pedidoId", pedido.getPedidoId());
       response.put("carritoId", carrito.getId()); 
       
       return ResponseEntity.ok(response);
   }
   
   
    

}
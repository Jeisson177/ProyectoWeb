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
    public ResponseEntity<Map<String, Long>>  agregarProductoAlCarrito(
            @RequestParam Long clienteId,
            @RequestParam Long productoId,
            @RequestParam int cantidad,
            @RequestBody(required = false) List<Long> adicionalesIds) {
                if (adicionalesIds == null) {
                    adicionalesIds = new ArrayList<>();  // Esto asegura que no haya un valor nulo al procesar
                }
                Carrito carrito = carritoService.agregarProducto(clienteId, productoId, cantidad, adicionalesIds);
                Pedido pedido1 = new Pedido();
                pedido1.setCliente(clienteService.getClienteById(clienteId));
                pedido1.setOperador(null);
                pedido1.setDomiciliario(null);
                pedido1.setEstado("RECIBIDO");
                pedido1.setDireccionEnvio("Calle 123");
                pedido1.setFecha(LocalDateTime.now());
                List<ItemPedido> itemsPedido = carrito.getItems().stream()
                    .map(itemCarrito -> {
                        ItemPedido itemPedido = new ItemPedido();
                        itemPedido.setPedido(pedido1);
                        itemPedido.setProducto(itemCarrito.getProducto());
                        itemPedido.setCantidad(itemCarrito.getCantidad());
                        itemPedido.setPrecioUnitario(itemCarrito.getProducto().getPrecio());
                        return itemPedido;
                    })
                    .collect(Collectors.toList());
                Map<String, Long> response = new HashMap<>();
                pedido1.setItems(itemsPedido);
                pedidoRepository.save(pedido1);

                response.put("pedidoId", pedido1.getPedidoId());
                response.put("carritoId", carrito.getId()); 
            
                return ResponseEntity.ok(response);
    }
    

}
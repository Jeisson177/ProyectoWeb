package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Pedido;
import com.example.demo.service.PedidoService;
import com.example.demo.service.ProductoService;


@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://localhost:4200")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ProductoService productoService;


    @Autowired
    public PedidoController(PedidoService pedidoService, ProductoService productoService) {
        this.pedidoService = pedidoService;
        this.productoService = productoService;
    }


    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoService.obtenerTodosLosPedidos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        var pedido = pedidoService.getPedidoById(id);
        return pedido.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PatchMapping("/{pedidoId}/asignar-domiciliario/{domiciliarioId}")
    public ResponseEntity<Void> asignarDomiciliario(
            @PathVariable Long pedidoId,
            @PathVariable Long domiciliarioId) {
        pedidoService.asignarDomiciliario(pedidoId, domiciliarioId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<Pedido>> getPedidoByClienteId(@PathVariable Long id) {
        List<Pedido> pedidos = pedidoService.obtenerPedidosPorCliente(id);
        return ResponseEntity.ok(pedidos);    }

    /*@PostMapping
    public Pedido createPedido(@RequestBody Long carritoId, @RequestParam String direccionEnvio) {
        return pedidoService.crearPedidoDesdeCarrito(carritoId, direccionEnvio);
    }*/
    @GetMapping("/operador/{operadorId}")
    public List<Pedido> getPedidosByOperador(@PathVariable Long operadorId) {
        return pedidoService.obtenerPedidosByOperador(operadorId);
    }
    @GetMapping("/domiciliario/{domiciliarioId}")
    public List<Pedido> getPedidosByDomiciliario(@PathVariable Long domiciliarioId) {
        return pedidoService.obtenerPedidosByDomiciliario(domiciliarioId);
    }
    @GetMapping("/pendientes")
    public List<Pedido> getPedidosPendientes() {
        return pedidoService.obtenerPedidosPendientes();
    }

     // --- FLUJO HISTORIAL ---
 
     @GetMapping("/{id}/detalle")
     public ResponseEntity<Optional<Pedido>> getDetallePedido(@PathVariable Long id) {
         return ResponseEntity.ok(pedidoService.obtenerPedidoPorId(id));
     }
 
     // --- FLUJO COMPRA ---
    @PostMapping("/crear-desde-carrito")
    public ResponseEntity<Pedido> crearPedidoDesdeCarrito(
            @RequestBody Long carritoId,
            @RequestParam String direccionEnvio) {
        Pedido pedidoCreado = pedidoService.crearPedidoDesdeCarrito(carritoId,direccionEnvio);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCreado);
    }
     // --- GESTIÓN DE ESTADOS ---
     @PatchMapping("/{id}/estado")
     public ResponseEntity<Void> actualizarEstado(
             @PathVariable Long id,
             @RequestParam String  nuevoEstado) {
         pedidoService.actualizarEstadoPedido(id, nuevoEstado);
         return ResponseEntity.noContent().build();
     }
 
     


}
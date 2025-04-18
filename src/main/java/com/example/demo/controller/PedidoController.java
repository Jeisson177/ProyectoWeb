package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Pedido;
import com.example.demo.service.PedidoService;
import com.example.demo.service.ProductoService;


@RestController
@RequestMapping("/pedidos")
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
        return pedidoService.getAllPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        return pedidoService.getPedidoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pedido createPedido(@RequestBody Pedido pedido) {
        return pedidoService.createPedido(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        pedido.setPedido_id(id);
        return ResponseEntity.ok(pedidoService.updatePedido(pedido));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Pedido> updateEstado(@PathVariable Long id, @RequestParam boolean estado) {
        return ResponseEntity.ok(pedidoService.updatePedidoStatus(id, estado));
    }

    @GetMapping("/estado")
    public List<Pedido> getPedidosByEstado(@RequestParam boolean estado) {
        return pedidoService.getPedidosByEstado(estado);
    }

    @GetMapping("/operador/{operadorId}")
    public List<Pedido> getPedidosByOperador(@PathVariable int operadorId) {
        return pedidoService.getPedidosByOperador(operadorId);
    }
    @GetMapping("/domiciliario/{domiciliarioId}")
    public List<Pedido> getPedidosByDomiciliario(@PathVariable int domiciliarioId) {
        return pedidoService.getPedidosByDomiciliario(domiciliarioId);
    }
    @GetMapping("/pendientes")
    public List<Pedido> getPedidosPendientes() {
        return pedidoService.getPedidosPendientes();
    }

     // --- FLUJO HISTORIAL ---
     @GetMapping("/cliente/{clienteId}")
     public List<Pedido> getHistorialCliente(
             @PathVariable Long clienteId,
             @RequestParam(required = false) String estado) {
         return estado != null ? 
                pedidoService.getPedidosByClienteAndEstado(clienteId, Boolean.parseBoolean(estado)) :
                pedidoService.getPedidosByCliente(clienteId);
     }
 
     @GetMapping("/{id}/detalle")
     public ResponseEntity<PedidoDetalleDTO> getDetallePedido(@PathVariable Long id) {
         return ResponseEntity.ok(pedidoService.getDetallePedido(id));
     }
 
     // --- FLUJO COMPRA ---
     @PostMapping("/crear-desde-carrito")
     public ResponseEntity<Pedido> crearPedidoDesdeCarrito(
             @RequestBody CarritoDTO carritoDTO) {
         // 1. Validar stock
         productoService.verificarDisponibilidad(carritoDTO.getItems());
         
         // 2. Crear pedido
         Pedido pedidoCreado = pedidoService.crearPedidoDesdeCarrito(carritoDTO);
         
         // 3. Actualizar stock
         productoService.actualizarStockPorPedido(carritoDTO.getItems());
         
         return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCreado);
     }
 
     // --- GESTIÓN DE ESTADOS ---
     @PatchMapping("/{id}/estado")
     public ResponseEntity<Void> actualizarEstado(
             @PathVariable Long id,
             @RequestParam String nuevoEstado) { // Ej: "PREPARACION", "EN_CAMINO", etc.
         pedidoService.actualizarEstado(id, nuevoEstado);
         return ResponseEntity.noContent().build();
     }
 
     @GetMapping("/estados/disponibles")
     public List<String> getEstadosDisponibles() {
         return Arrays.asList("PENDIENTE", "PREPARACION", "EN_CAMINO", "ENTREGADO", "CANCELADO");
     }


}
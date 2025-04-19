package com.example.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Adicional;
import com.example.demo.entity.Carrito;
import com.example.demo.entity.ItemCarrito;
import com.example.demo.entity.Producto;
import com.example.demo.repository.AdicionalRepository;
import com.example.demo.repository.CarritoRepository;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ItemCarritoRepository;
import com.example.demo.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class CarritoServiceImp implements CarritoService {

    private final CarritoRepository carritoRepository;
    private final ItemCarritoRepository itemCarritoRepository;
    private final ProductoRepository productoRepository;
    private final AdicionalRepository adicionalRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public CarritoServiceImp(CarritoRepository carritoRepository,
                           ItemCarritoRepository itemCarritoRepository,
                           ProductoRepository productoRepository,
                           AdicionalRepository adicionalRepository,
                           ClienteRepository clienteRepository) {
        this.carritoRepository = carritoRepository;
        this.itemCarritoRepository = itemCarritoRepository;
        this.productoRepository = productoRepository;
        this.adicionalRepository = adicionalRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public Carrito obtenerCarrito(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con ID: " + clienteId);
        }
        
        return carritoRepository.findByClienteId(clienteId)
                .orElseGet(() -> {
                    Carrito nuevoCarrito = new Carrito();
                    nuevoCarrito.setClienteId(clienteId);
                    return carritoRepository.save(nuevoCarrito);
                });
    }

    @Override
    @Transactional
    public Carrito agregarProducto(Long clienteId, Long productoId, int cantidad, List<Long> adicionalesIds) {
        // Validar cantidad
        if (cantidad <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad debe ser mayor a cero");
        }
        
        // Obtener o crear carrito
        Carrito carrito = obtenerCarrito(clienteId);
        
        // Obtener producto
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                    "Producto no encontrado con ID: " + productoId));
        
        // Obtener y validar adicionales
        List<Adicional> adicionales = obtenerYValidarAdicionales(producto, adicionalesIds);
        
        // Verificar si el producto ya está en el carrito
        Optional<ItemCarrito> itemExistente = itemCarritoRepository.findByCarritoIdAndProductoId(carrito.getId(), productoId);
        
        if (itemExistente.isPresent()) {
            // Actualizar cantidad si el producto ya está en el carrito
            ItemCarrito item = itemExistente.get();
            item.setCantidad(item.getCantidad() + cantidad);
            itemCarritoRepository.save(item);
        } else {
            // Crear nuevo item
            ItemCarrito nuevoItem = new ItemCarrito(carrito, producto, cantidad, adicionales);
            itemCarritoRepository.save(nuevoItem);
        }
        
        return carritoRepository.save(carrito);
    }

    private List<Adicional> obtenerYValidarAdicionales(Producto producto, List<Long> adicionalesIds) {
        if (adicionalesIds == null || adicionalesIds.isEmpty()) {
            return Collections.emptyList();
        }
        
        List<Adicional> adicionales = adicionalRepository.findAllById(adicionalesIds);
        
        // Verificar que todos los adicionales solicitados existen
        if (adicionales.size() != adicionalesIds.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                "Uno o más adicionales no existen");
        }
        
        // Verificar que los adicionales pertenecen al producto
        List<Adicional> adicionalesValidos = producto.getAdicionales();
        for (Adicional adicional : adicionales) {
            if (!adicionalesValidos.contains(adicional)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "El adicional '" + adicional.getNombre() + "' no está disponible para este producto");
            }
        }
        
        return adicionales;
    }

    @Override
    @Transactional
    public Carrito actualizarCantidad(Long clienteId, Long itemCarritoId, int cantidad) {
        if (cantidad <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad debe ser mayor a cero");
        }
        
        Carrito carrito = obtenerCarrito(clienteId);
        ItemCarrito item = itemCarritoRepository.findByIdAndCarritoId(itemCarritoId, carrito.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                    "Ítem no encontrado en el carrito con ID: " + itemCarritoId));
        
        item.setCantidad(cantidad);
        itemCarritoRepository.save(item);
        
        return carrito;
    }

    @Override
    @Transactional
    public void eliminarItem(Long clienteId, Long itemCarritoId) {
        Carrito carrito = obtenerCarrito(clienteId);
        ItemCarrito item = itemCarritoRepository.findByIdAndCarritoId(itemCarritoId, carrito.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                    "Ítem no encontrado en el carrito con ID: " + itemCarritoId));
        
        itemCarritoRepository.delete(item);
    }

    @Override
    @Transactional
    public void limpiarCarrito(Long clienteId) {
        Carrito carrito = obtenerCarrito(clienteId);
        itemCarritoRepository.deleteByCarritoId(carrito.getId());
    }

    @Override
    public List<ItemCarrito> obtenerItemsCarrito(Long clienteId) {
        Carrito carrito = obtenerCarrito(clienteId);
        return itemCarritoRepository.findByCarritoIdWithProductAndAdicionales(carrito.getId());
    }
}
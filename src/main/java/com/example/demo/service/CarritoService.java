package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Carrito;
import com.example.demo.entity.ItemCarrito;



public interface CarritoService {
    
    Carrito obtenerCarrito(Long clienteId);
    Carrito agregarProducto(Long clienteId, Long productoId, int cantidad, List<Long> adicionalesIds);
    Carrito actualizarCantidad(Long clienteId, Long itemCarritoId, int cantidad);
    void eliminarItem(Long clienteId, Long itemCarritoId);
    void limpiarCarrito(Long clienteId);
    List<ItemCarrito> obtenerItemsCarrito(Long clienteId);}
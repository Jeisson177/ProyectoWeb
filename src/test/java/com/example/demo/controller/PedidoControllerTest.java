package com.example.demo.controller;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Pedido;
import com.example.demo.service.PedidoService;
import com.example.demo.service.ProductoService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.BDDMockito.given;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @MockBean
    private ProductoService productoService;

    @Test
    void testGetAllPedidos() throws Exception {
        List<Pedido> pedidos = Arrays.asList(new Pedido(), new Pedido());
        given(pedidoService.obtenerTodosLosPedidos()).willReturn(pedidos);

        mockMvc.perform(get("/pedidos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetPedidoByIdFound() throws Exception {
        // 1. Configuración del test
        Pedido pedido = new Pedido();
        pedido.setPedidoId(1L); 
        
        // Mock del servicio
        given(pedidoService.getPedidoById(1L)).willReturn(Optional.of(pedido));

        // 2. Ejecución y verificación
        mockMvc.perform(get("/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pedidoId").value(1)); 
    }

    @Test
    void testGetPedidoByIdNotFound() throws Exception {
        given(pedidoService.getPedidoById(99L)).willReturn(Optional.empty());

        mockMvc.perform(get("/pedidos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPedidoByClienteId() throws Exception {
       
        Cliente cliente = new Cliente();
        cliente.setId(5L); 
        
        Pedido pedido = new Pedido();
        pedido.setPedidoId(1L);
        pedido.setCliente(cliente); 
        
       
        given(pedidoService.obtenerPedidosPorCliente(5L)).willReturn(List.of(pedido));

        
        mockMvc.perform(get("/pedidos/cliente/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))) 
                .andExpect(jsonPath("$[0].pedidoId").value(1))
                .andExpect(jsonPath("$[0].cliente.id").value(5)); 
    }

    @Test
    void testActualizarEstadoPedido() throws Exception {
        mockMvc.perform(patch("/pedidos/1/estado")
                .param("nuevoEstado", "ENTREGADO"))
                .andExpect(status().isNoContent());

        Mockito.verify(pedidoService).actualizarEstadoPedido(1L, "ENTREGADO");
    }
}

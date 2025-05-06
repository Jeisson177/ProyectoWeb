package com.example.demo.controller;

import com.example.demo.entity.Pedido;
import com.example.demo.service.PedidoService;
import com.example.demo.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        Pedido pedido = new Pedido();
        pedido.setPedidoId(1L);
        given(pedidoService.getPedidoById(1L)).willReturn(Optional.of(pedido));

        mockMvc.perform(get("/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetPedidoByIdNotFound() throws Exception {
        given(pedidoService.getPedidoById(99L)).willReturn(Optional.empty());

        mockMvc.perform(get("/pedidos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPedidoByClienteId() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setPedidoId(1L);
        given(pedidoService.obtenerPedidosPorCliente(5L)).willReturn(List.of(pedido));

        mockMvc.perform(get("/pedidos/cliente/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testActualizarEstadoPedido() throws Exception {
        mockMvc.perform(patch("/pedidos/1/estado")
                .param("nuevoEstado", "ENTREGADO"))
                .andExpect(status().isNoContent());

        Mockito.verify(pedidoService).actualizarEstadoPedido(1L, "ENTREGADO");
    }
}

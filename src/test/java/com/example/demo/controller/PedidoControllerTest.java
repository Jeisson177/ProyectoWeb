package com.example.demo.controller;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.Pedido;
import com.example.demo.service.PedidoService;

//Pruebas unitarias 

//Pruebas de integración
@WebMvcTest(controllers = PedidoController.class)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Test
    public void PedidoController_getAllPedidos_PedidoList() throws Exception {
        // Implementar la prueba para el método getAllPedidos
        when(pedidoService.obtenerTodosLosPedidos()).thenReturn(
            List.of(
                new Pedido(
                    
                )
        )
        );
    }
    
}

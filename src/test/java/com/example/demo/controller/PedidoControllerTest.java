package com.example.demo.controller;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import static org.h2.store.fs.FilePath.get;
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

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Domiciliario;
import com.example.demo.entity.Operador;
import com.example.demo.entity.Pedido;
import com.example.demo.service.PedidoService;

import io.swagger.v3.oas.models.media.MediaType;

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
    // Primer pedido
    Pedido pedido1 = new Pedido();
    pedido1.setPedidoId(1L);
    pedido1.setEstado("RECIBIDO");
    pedido1.setDireccionEnvio("Calle Falsa 123");
    pedido1.setFecha(LocalDateTime.of(2023, 5, 1, 10, 30));

    Cliente cliente1 = new Cliente();
    cliente1.setId(1L);
    cliente1.setNombre("Juan Perez");
    pedido1.setCliente(cliente1);

    Operador operador1 = new Operador();
    operador1.setId(1L);
    operador1.setNombre("Maria");
    pedido1.setOperador(operador1);

    Domiciliario domiciliario1 = new Domiciliario();
    domiciliario1.setId(1L);
    domiciliario1.setNombre("Carlos");
    pedido1.setDomiciliario(domiciliario1);

    // Segundo pedido
    Pedido pedido2 = new Pedido();
    pedido2.setPedidoId(2L);
    pedido2.setEstado("COCINANDO");
    pedido2.setDireccionEnvio("Avenida Siempre Viva 742");
    pedido2.setFecha(LocalDateTime.of(2023, 5, 2, 12, 45));

    Cliente cliente2 = new Cliente();
    cliente2.setId(2L);
    cliente2.setNombre("Ana Gómez");
    pedido2.setCliente(cliente2);

    Operador operador2 = new Operador();
    operador2.setId(2L);
    operador2.setNombre("Luis");
    pedido2.setOperador(operador2);

    Domiciliario domiciliario2 = new Domiciliario();
    domiciliario2.setId(2L);
    domiciliario2.setNombre("Pedro");
    pedido2.setDomiciliario(domiciliario2);

    // Tercer pedido
    Pedido pedido3 = new Pedido();
    pedido3.setPedidoId(3L);
    pedido3.setEstado("ENTREGADO");
    pedido3.setDireccionEnvio("Calle Luna 555");
    pedido3.setFecha(LocalDateTime.of(2023, 5, 3, 14, 0));

    Cliente cliente3 = new Cliente();
    cliente3.setId(3L);
    cliente3.setNombre("Lucía Torres");
    pedido3.setCliente(cliente3);

    Operador operador3 = new Operador();
    operador3.setId(3L);
    operador3.setNombre("Camila");
    pedido3.setOperador(operador3);

    Domiciliario domiciliario3 = new Domiciliario();
    domiciliario3.setId(3L);
    domiciliario3.setNombre("Javier");
    pedido3.setDomiciliario(domiciliario3);

    // Mockear el servicio
    when(pedidoService.obtenerTodosLosPedidos()).thenReturn(List.of(pedido1, pedido2, pedido3));

    // Ejecutar la solicitud y verificar los resultados
    mockMvc.perform(get("/all") // Asegúrate de que esta ruta sea correcta
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(3))
        .andExpect(jsonPath("$[0].pedidoId").value(1L))
        .andExpect(jsonPath("$[1].estado").value("COCINANDO"))
        .andExpect(jsonPath("$[2].direccionEnvio").value("Calle Luna 555"));
}

    
}

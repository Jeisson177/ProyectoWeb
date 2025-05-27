package com.example.demo.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.Domiciliario;
import com.example.demo.entity.Pedido;
import com.example.demo.repository.CarritoRepository;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.DomiciliarioRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.service.CarritoService;
import com.example.demo.service.PedidoServiceImp;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTestMock {

    @InjectMocks
    private PedidoServiceImp pedidoService;

    @Mock
    private CarritoRepository carritoRepository;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private CarritoService carritoService;

    @Mock
    private DomiciliarioRepository domiciliarioRepository;

    @Test
    public void testActualizarEstadoPedidoConMocks() {
        Pedido pedido = new Pedido();
        pedido.setPedidoId(1L);
        pedido.setEstado("RECIBIDO");

        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        pedidoService.actualizarEstadoPedido(1L, "EN CAMINO");

        Assertions.assertEquals("EN CAMINO", pedido.getEstado());
        Mockito.verify(pedidoRepository).save(pedido);
    }

    @Test
    public void testAsignarDomiciliarioConMocks() {
        Pedido pedido = new Pedido();
        pedido.setPedidoId(1L);
        pedido.setEstado("RECIBIDO");

        Domiciliario domiciliario = new Domiciliario();
        domiciliario.setId(2L);
        domiciliario.setdisponibilidad(true);

        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        Mockito.when(domiciliarioRepository.findById(2L)).thenReturn(Optional.of(domiciliario));

        pedidoService.asignarDomiciliario(1L, 2L);

        Assertions.assertEquals("EN CAMINO", pedido.getEstado());
        Assertions.assertFalse(domiciliario.isdisponibilidad());
        Mockito.verify(pedidoRepository).save(pedido);
        Mockito.verify(domiciliarioRepository).save(domiciliario);
    }

    @Test
    public void testFinalizarPedidoConMocks() {
        Domiciliario domiciliario = new Domiciliario();
        domiciliario.setId(2L);
        domiciliario.setdisponibilidad(false);

        Pedido pedido = new Pedido();
        pedido.setPedidoId(1L);
        pedido.setEstado("EN CAMINO");
        pedido.setDomiciliario(domiciliario);

        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        pedidoService.finalizarPedido(1L);

        Assertions.assertEquals("ENTREGADO", pedido.getEstado());
        Assertions.assertTrue(domiciliario.isdisponibilidad());
        Mockito.verify(pedidoRepository).save(pedido);
        Mockito.verify(domiciliarioRepository).save(domiciliario);
    }

    public void testObtenerPedidoPorIdConMocks() {
        Pedido pedido = new Pedido();
        pedido.setPedidoId(1L);
        pedido.setEstado("RECIBIDO");

        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Optional<Pedido> resultado = pedidoService.obtenerPedidoPorId(1L);

        Assertions.assertTrue(resultado.isPresent());
        Assertions.assertEquals("RECIBIDO", resultado.get().getEstado());
    }

    @Test
    public void testObtenerPedidoPorIdNoEncontrado() {
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Pedido> resultado = pedidoService.obtenerPedidoPorId(1L);

        Assertions.assertFalse(resultado.isPresent());
    }

    @Test
public void testAsignarDomiciliarioNoDisponible() {
    Pedido pedido = new Pedido();
    pedido.setPedidoId(1L);
    pedido.setEstado("RECIBIDO");

    Domiciliario domiciliario = new Domiciliario();
    domiciliario.setId(2L);
    domiciliario.setdisponibilidad(false); // No disponible

    Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
    Mockito.when(domiciliarioRepository.findById(2L)).thenReturn(Optional.of(domiciliario));

    pedidoService.asignarDomiciliario(1L, 2L);

    // El estado no debería cambiar
    Assertions.assertEquals("RECIBIDO", pedido.getEstado());
    Assertions.assertFalse(domiciliario.isdisponibilidad());

    Mockito.verify(pedidoRepository, Mockito.never()).save(Mockito.any());
    Mockito.verify(domiciliarioRepository, Mockito.never()).save(Mockito.any());
}


}

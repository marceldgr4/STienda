package com.Unimagda.STienda.RepositoryTest;

import com.Unimagda.STienda.AbstractIntegrationBDTest;
import com.Unimagda.STienda.Entity.DetalleEnvio;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import com.Unimagda.STienda.Entity.Pedido;
import com.Unimagda.STienda.Repository.DetalleEnvioRepository;
import com.Unimagda.STienda.Repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class DetalleEnvioRepositoryTest extends AbstractIntegrationBDTest {

    private final DetalleEnvioRepository detalleEnvioRepository;
    private final PedidoRepository pedidoRepository;
    @Autowired
    DetalleEnvioRepositoryTest(DetalleEnvioRepository detalleEnvioRepository,PedidoRepository pedidoRepository) {
        this.detalleEnvioRepository = detalleEnvioRepository;
        this.pedidoRepository= pedidoRepository;
    }
    @BeforeEach
    public void setUP(){
        pedidoRepository.deleteAll();;
        detalleEnvioRepository.deleteAll();
    }
    public void mockData(){
        DetalleEnvio detalleEnvio = DetalleEnvio.builder()
                .Direccion("direccion")
                .NumeroDeGuia(1234l)
                .Transportadora("transportadora")
                .EstadoDePedido(EstadoDePedido.ENVIADO)
                .build();
        detalleEnvioRepository.save(detalleEnvio);
        DetalleEnvio detalleEnvio1 = DetalleEnvio.builder()
                .Direccion("direccion1")
                .NumeroDeGuia(123l)
                .Transportadora("transportadora2")
                .EstadoDePedido(EstadoDePedido.PENDIENTE)
                .build();
        detalleEnvioRepository.save(detalleEnvio1);
        detalleEnvioRepository.flush();
    }


    @DisplayName("JUnit test dado DetallesEnvio_cuando Guardar_entoncesId Detalles No Nulo")
    @Test
    public void dadoDetallesEnvio_cuandoGuardar_entoncesIdDetallesNoNulo() {
        mockData();
        DetalleEnvio detalleEnvio = detalleEnvioRepository.findAll().get(0);
        assertNotNull(detalleEnvio.getId());
    }

    @DisplayName("JUnit test dado DetallesEnvio_cuando Buscar Por OrdenId_ entonces Detalles")
    @Test
    public void dadoDetallesEnvio_cuandoBuscarPorOrdenId_entoncesDetalles(){
        mockData();
        Pedido pedido = Pedido.builder()
                .Status(EstadoDePedido.PENDIENTE)
                .FechaPedido(LocalDateTime.now())
                .build();
        Pedido pedidoSave = pedidoRepository.save(pedido);
        DetalleEnvio detalleEnvio = detalleEnvioRepository.findAll().get(0);
        detalleEnvio.setPedido(pedidoSave);
        detalleEnvioRepository.flush();

        List<DetalleEnvio> ListaDetalleEnvio = detalleEnvioRepository.findByPedidoId(pedidoSave.getId());
        assertThat(ListaDetalleEnvio).isNotEmpty();
        assertEquals(ListaDetalleEnvio.get(0).getPedido(),pedidoSave.getId());
        assertNotEquals(1234l, (long) ListaDetalleEnvio.get(0).getNumeroDeGuia());

    }
    @DisplayName("JUnit test dado DetallesEnvio_cuando Buscar Por Transportista_entoncesDetalles")
    @Test
    public void dadoDetallesEnvio_cuandoBuscarPorTransportista_entoncesDetalles(){
        mockData();
        List<DetalleEnvio> ListaDetalleEnvio = detalleEnvioRepository.findByTransportadora("transportadora");
        assertThat(ListaDetalleEnvio).isNotEmpty();
        assertEquals("transportadora", ListaDetalleEnvio.get(0).getTransportadora());

    }
    @DisplayName("dado DetallesEnvio_cuando Buscar Por Estado Pedido_entoncesDetalles")
    @Test
    public void dadoDetallesEnvio_cuandoBuscarPorEstadoPedido_entoncesDetalles(){
      mockData();
      Pedido pedido = Pedido.builder()
              .Status(EstadoDePedido.PENDIENTE)
              .FechaPedido(LocalDateTime.now())
              .build();
      Pedido pedidoSave = pedidoRepository.save(pedido);
      DetalleEnvio detalleEnvio = detalleEnvioRepository.findAll().get(0);
      detalleEnvio.setPedido(pedidoSave);
      detalleEnvioRepository.flush();
      List<DetalleEnvio> ListaDetalleEnvio = detalleEnvioRepository.findByEstadoDePedido(pedidoSave.getStatus());
      assertNotEquals(123l, (long) ListaDetalleEnvio.get(0).getNumeroDeGuia());
    }
    @DisplayName("dadoIdDetallesEnvio_cuandoBuscarPorId_entoncesDetalles")
    @Test
    public void dadoIdDetallesEnvio_cuandoBuscarPorId_entoncesDetalles(){
        DetalleEnvio detalleEnvio = detalleEnvioRepository.findAll().get(0);
        Optional<DetalleEnvio>detalleEnvioOptional = detalleEnvioRepository.findById(detalleEnvio.getId());
        assertEquals(detalleEnvio.getId(), detalleEnvioOptional.get().getId());
    }
    @DisplayName("dadoIdDetalles_cuandoEliminarPorId_entoncesDetallesEliminados")
    @Test
    public void dadoIdDetalles_cuandoEliminarPorId_entoncesDetallesEliminados(){
        mockData();
        DetalleEnvio detalleEnvio = detalleEnvioRepository.findAll().get(0);
        detalleEnvioRepository.deleteById(detalleEnvio.getId());
        Optional<DetalleEnvio> ListaDetalleEnvio = detalleEnvioRepository.findById(detalleEnvio.getId());
        assertThat(ListaDetalleEnvio).isEmpty();
    }
}

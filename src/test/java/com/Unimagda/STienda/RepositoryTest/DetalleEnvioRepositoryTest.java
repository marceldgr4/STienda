package com.Unimagda.STienda.RepositoryTest;

import com.Unimagda.STienda.AbstractIntegrationBDTest;
import com.Unimagda.STienda.Entity.DetalleEnvio;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import com.Unimagda.STienda.Entity.Pedido;
import com.Unimagda.STienda.Repository.Repositorys.DetalleEnvioRepository;
import com.Unimagda.STienda.Repository.Repositorys.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class DetalleEnvioRepositoryTest extends AbstractIntegrationBDTest {
    private final DetalleEnvioRepository detalleEnvioRepository;
    private final PedidoRepository pedidoRepository;
    @Autowired
    DetalleEnvioRepositoryTest(DetalleEnvioRepository detalleEnvioRepository, PedidoRepository pedidoRepository) {
        this.detalleEnvioRepository = detalleEnvioRepository;
        this.pedidoRepository = pedidoRepository;
    }
    @BeforeEach
    void setUp() {
        pedidoRepository.deleteAll();
        detalleEnvioRepository.deleteAll();
    }
    void crearDatos() {
        DetalleEnvio detalleEnvio = DetalleEnvio.builder()
                .Transportadora("transportador")
                .NumeroDeGuia(12345L)
                .Direccion("direccion")
                .build();
        detalleEnvioRepository.save(detalleEnvio);
        DetalleEnvio detalleEnvio1 = DetalleEnvio.builder()
                .Transportadora("transportador1")
                .NumeroDeGuia(1234445L)
                .Direccion("direccion")
                .build();
        detalleEnvioRepository.save(detalleEnvio1);
        detalleEnvioRepository.flush();
    }
    @Test
    void dadoDetalleEnvio_cuandoSeGuarda_elIdDetalleEnvio() {
        crearDatos();
        DetalleEnvio detalleEnvio = detalleEnvioRepository.findAll().get(0);
        assertNotNull(detalleEnvio.getIdDetalleEnvio());
    }
    @Test
    void dedoDetalleEnvio_cuandoSeBuscaPedido_por_IdPedido_en_DetalleEnvio(){
        crearDatos();
        Pedido pedido = Pedido.builder()
                .estadoDePedido(EstadoDePedido.PENDIENTE)
                .FechaPedido(LocalDateTime.now())
                .build();
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        DetalleEnvio detalleEnvio = detalleEnvioRepository.findAll().get(0);
        detalleEnvio.setPedido(pedidoGuardado);
        detalleEnvioRepository.flush();

        List<DetalleEnvio> detalleEnvioList = detalleEnvioRepository.findByPedido_IdPedido(pedidoGuardado.getIdPedido());
        assertThat(detalleEnvioList).isEmpty();
        assertEquals(detalleEnvioList.get(0).getPedido().getIdPedido(),pedidoGuardado.getIdPedido());
        assertNotEquals(1234445L,(long) detalleEnvioList.get(0).getNumeroDeGuia());
    }
    @Test
    void dadoDetalleEnvio_CuandoSeBuscaPorTransportadora_en_DetalleEnvio(){
        crearDatos();
        List<DetalleEnvio> detalleEnvioList = detalleEnvioRepository.findByTransportadora("transportadora1");
        assertThat(detalleEnvioList).isEmpty();
        assertEquals("transportadora1",detalleEnvioList.get(0).getTransportadora());
    }
    @Test
    void dadoDetalleEnvio_CuandoSeBuscaPor_idPedido_Status_en_DetalleEnvio(){
        crearDatos();
        Pedido pedido = Pedido.builder()
                .estadoDePedido(EstadoDePedido.PENDIENTE)
                .FechaPedido(LocalDateTime.now())
                .build();
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        DetalleEnvio detalleEnvio = detalleEnvioRepository.findAll().get(0);
        detalleEnvio.setPedido(pedidoGuardado);
        detalleEnvioRepository.flush();

        List<DetalleEnvio> detalleEnvioList = detalleEnvioRepository.findByEstadoDePedido(pedidoGuardado.getEstadoDePedido());
        assertThat(detalleEnvioList).isNotEmpty();
        assertEquals(pedidoGuardado.getEstadoDePedido(),detalleEnvioList.get(0).getPedido().getEstadoDePedido());
        assertNotEquals(1234445L,(long) detalleEnvioList.get(0).getNumeroDeGuia());
    }
    @Test
    void dadoIdDetalleEnvio_cuandoSeBusca_por_ID_en_DetalleEnvio(){
        crearDatos();
        DetalleEnvio detalleEnvio = detalleEnvioRepository.findAll().get(0);
        Optional<DetalleEnvio>detalleEnvioOptional= detalleEnvioRepository.findById(detalleEnvio.getIdDetalleEnvio());
        assertEquals(detalleEnvio.getIdDetalleEnvio(),detalleEnvioOptional.get().getIdDetalleEnvio());
    }
    @Test
    void  dadoIdDetalleEnvio_cuandoSeEliminaID_en_DetalleEnvio(){
        crearDatos();
        DetalleEnvio detalleEnvio = detalleEnvioRepository.findAll().get(0);
        detalleEnvioRepository.deleteById(detalleEnvio.getIdDetalleEnvio());
        Optional<DetalleEnvio> detalleEnvioOptional = detalleEnvioRepository.findById(detalleEnvio.getIdDetalleEnvio());
        assertThat(detalleEnvioOptional).isEmpty();
    }

}

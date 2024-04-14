package com.Unimagda.STienda.ServiceTest;

import com.Unimagda.STienda.DTO.Save.DetalleEnvioDtoSave;
import com.Unimagda.STienda.DTO.Send.DetalleEnvioDtoSend;
import com.Unimagda.STienda.Entity.DetalleEnvio;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import com.Unimagda.STienda.Entity.Pedido;
import com.Unimagda.STienda.Mapper.Mappers.DetalleEnvioMapper;
import com.Unimagda.STienda.Repository.Repositorys.DetalleEnvioRepository;
import com.Unimagda.STienda.Repository.Repositorys.PedidoRepository;
import com.Unimagda.STienda.Service.Implements.DetalleEnvioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DetalleEnvioServiceTest {
@Mock
    DetalleEnvioRepository detalleEnvioRepository;
@Mock
    DetalleEnvioMapper detalleEnvioMapper;
@Mock
    PedidoRepository pedidoRepository;
@InjectMocks
    DetalleEnvioServiceImpl detalleEnvioServiceimpl;
DetalleEnvio detalleEnvio;
DetalleEnvioDtoSave detalleEnvioDtoSave;
DetalleEnvioDtoSend detalleEnvioDtoSend;
Pedido pedido;

        @BeforeEach
            public void setUp() {
            pedido = Pedido.builder()
                    .idPedido(1L)
                    .detalleEnvio(detalleEnvio)
                    .estadoDePedido(EstadoDePedido.PENDIENTE)
                    .build();
            detalleEnvio = DetalleEnvio.builder()
                    .idDetalleEnvio(1L)
                    .Transportadora("Transportadora")
                    .Direccion("Direccion")
                    .NumeroDeGuia(3342L)
                    .pedido(pedido)
                    .build();
            detalleEnvioDtoSave= DetalleEnvioDtoSave.builder()
                    .Direccion("Direccion")
                    .Transportadora("Transportadora")
                    .NumeroDeGuia(3342)
                    .build();
            detalleEnvioDtoSend = DetalleEnvioDtoSend.builder()
                    .idDetalleEnvio(1L)
                    .Direccion("Direccion")
                    .Transportadora("Transportadora")
                    .NumeroDeGuia(3342L)
                    .idPedido(1L)
                    .build();
        }
        @Test
    void dadoIdPedido_CuandoSeBuscaPedido_por_IdPedido_regresaDetalleEnvioDtoSendList(){
            when(detalleEnvioRepository.findByPedido_IdPedido(pedido.getIdPedido())).thenReturn(List.of(detalleEnvio));
            when(detalleEnvioMapper.ListEntityToDtoSend(List.of(detalleEnvio))).thenReturn(List.of(detalleEnvioDtoSend));

            List<DetalleEnvioDtoSend> detalleEnvioDtoSendList = detalleEnvioServiceimpl.ObtenerIdPedido(pedido.getIdPedido());
            assertEquals(List.of(detalleEnvioDtoSend),detalleEnvioDtoSendList);
            assertEquals(1, detalleEnvioDtoSendList.size());
            assertEquals(detalleEnvioDtoSend.getNumeroDeGuia(),detalleEnvioDtoSendList.get(0).getNumeroDeGuia());
        }
        @Test
    void dadoTransportadora_CuandoSeBuscaTransportadora_regresaDetalleEnvioDtoSendList(){
            when(detalleEnvioRepository.findByTransportadora(detalleEnvio.getTransportadora())).thenReturn(List.of(detalleEnvio));
            when(detalleEnvioMapper.ListEntityToDtoSend(List.of(detalleEnvio))).thenReturn(List.of(detalleEnvioDtoSend));

            List<DetalleEnvioDtoSend> detalleEnvioDtoSendList = detalleEnvioServiceimpl.
                    ObtenerDetalleDeEnvioPorTransportadora
                            (detalleEnvio.getTransportadora());
            assertEquals(List.of(detalleEnvioDtoSend),detalleEnvioDtoSendList);
            assertEquals(1, detalleEnvioDtoSendList.size());
            assertEquals(detalleEnvioDtoSend.getTransportadora(),detalleEnvioDtoSendList.get(0).getTransportadora());
        }
        @Test
    void dadoStatus_cuandoSeBuscaStatus_regresaDetalleEnvioDtoSendList(){
            when(detalleEnvioRepository.findByEstadoDePedido(pedido.getEstadoDePedido())).thenReturn(List.of(detalleEnvio));
            when(detalleEnvioMapper.ListEntityToDtoSend(List.of(detalleEnvio))).thenReturn(List.of(detalleEnvioDtoSend));
            List<DetalleEnvioDtoSend>detalleEnvioDtoSendList = detalleEnvioServiceimpl
                    .ObtenerDetalleDeEnvioPorEstado(pedido.getEstadoDePedido());
            assertEquals(List.of(detalleEnvioDtoSend),detalleEnvioDtoSendList);
            assertEquals(1, detalleEnvioDtoSendList.size());
            assertEquals(detalleEnvio.getPedido().getEstadoDePedido(),pedido.getEstadoDePedido());
        }
        @Test
    void dadoDetalleEnvioDtoSave_cuandoSeGuarda_regresaDetalleEnvioDtoSend(){
            when(pedidoRepository.findById(pedido.getIdPedido())).thenReturn(Optional.of(pedido));
            when(detalleEnvioMapper.dtoSaveToEntity(detalleEnvioDtoSave)).thenReturn(detalleEnvio);
            when(detalleEnvioRepository.save(detalleEnvio)).thenReturn(detalleEnvio);
            when(detalleEnvioMapper.EntityToDtoSend(detalleEnvio)).thenReturn(detalleEnvioDtoSend);
            DetalleEnvioDtoSend detalleEnvioDtoSend1 =detalleEnvioServiceimpl.save(detalleEnvioDtoSave,pedido.getIdPedido());
            assertEquals(detalleEnvioDtoSend,detalleEnvioDtoSend1);
            assertEquals(detalleEnvioDtoSend.getNumeroDeGuia(),detalleEnvioDtoSend1.getNumeroDeGuia());
            assertEquals(detalleEnvio.getPedido().getIdPedido(),pedido.getIdPedido());

        }
        @Test
    void dadoDetalleEnvioDtoSaveYID_cuandoSeGuardad_theThrowRunTimeException(){
            when(pedidoRepository.findById(pedido.getIdPedido())).thenReturn(Optional.of(pedido));
            assertThrows(RuntimeException.class,()-> detalleEnvioServiceimpl
                    .save(detalleEnvioDtoSave,pedido.getIdPedido()));
        }
        @Test
    void dadoDetalleEnvioDtoSave_cuandoSeActualiza_regresaDetalleEnvioDtoSend(){
            when(detalleEnvioRepository.findById(detalleEnvio.getIdDetalleEnvio()))
                    .thenReturn(Optional.of(detalleEnvio));
            when(detalleEnvioMapper.dtoSaveToEntity(detalleEnvioDtoSave)).thenReturn(detalleEnvio);
            when(detalleEnvioMapper.EntityToDtoSend(detalleEnvio)).thenReturn(detalleEnvioDtoSend);
            when(detalleEnvioRepository.save(any(DetalleEnvio.class))).thenReturn(detalleEnvio);

            DetalleEnvioDtoSend detalleEnvioDtoSendTest= detalleEnvioServiceimpl
                    .update(detalleEnvioDtoSave,detalleEnvio.getIdDetalleEnvio());
            assertEquals(detalleEnvioDtoSend,detalleEnvioDtoSendTest);
            assertNotNull(detalleEnvioDtoSendTest);
            assertEquals(detalleEnvio.getPedido(),detalleEnvioDtoSendTest.getIdDetalleEnvio());
        }
}

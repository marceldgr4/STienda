package com.Unimagda.STienda.Service;

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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DetalleEnvioServiceTest {
   /* @Mock
    DetalleEnvioRepository detalleEnvioRepository;
    @Mock
    DetalleEnvioMapper detalleEnvioMapper;
    @Mock
    PedidoRepository pedidoRepository;
    @InjectMocks
    DetalleEnvioServiceImpl detalleEnvioService;
    DetalleEnvio detalleEnvio;
    DetalleEnvioDtoSave detalleEnvioDtoSave;
    DetalleEnvioDtoSend detalleEnvioDtoSend;
    Pedido pedido;
    @BeforeEach
    void setUp() {
        pedido =pedido.builder()
                .idPedido(1l)
                .detalleEnvio(detalleEnvio)
                .Status(EstadoDePedido.PENDIENTE)
                .build();
        detalleEnvio = detalleEnvio.builder()
                .idDetalleEnvio(1l)
                .Direccion("direccion")
                .Transportadora("transportadora")
                .NumeroDeGuia(11231l)
                .pedido(pedido)
                .build();
        detalleEnvioDtoSave = DetalleEnvioDtoSave.builder()
                .Direccion("direccion")
                .Transportadora("transportadora1")
                .NumeroDeGuia(1233)
                .build();
        detalleEnvioDtoSend =detalleEnvioDtoSend.builder()
                .idDetalleEnvio(3l)
                .Direccion("direccion3")
                .Transportadora("transportadora3")
                .NumeroDeGuia(32312l)
                .idPedido(3l)
                .build();

    }
    @DisplayName("dadoIdPedido cuando Buscar Por Pedido IdPedido_entonces Lista EnvioDetallesDtoSend")
    @Test
    public  void dadoIdPedido_cuandoBuscarPorPedido_IdPedido_entoncesListaDetalleEnvioDtoSend(){
        when(detalleEnvioRepository
                .findByPedidoId(pedido.getIdPedido()))
                .thenReturn(List.of(detalleEnvio));

        when(detalleEnvioMapper
                .detalleEnvioToDetalleEnvioDtoSend((DetalleEnvio) List.of(detalleEnvio)))
                .thenReturn((DetalleEnvioDtoSend) List.of(detalleEnvioDtoSend));
        List<DetalleEnvioDtoSend> detalleEnvioDtoSendList =detalleEnvioService.ObtenerDetalleEnvioPorId(pedido.getIdPedido());

        assertEquals(List.of(detalleEnvioDtoSend), detalleEnvioDtoSendList);
        assertEquals(1, detalleEnvioDtoSendList.size());
        assertEquals(detalleEnvioDtoSend.getNumeroDeGuia(),detalleEnvioDtoSendList.get(0).getNumeroDeGuia());

    }
    @DisplayName("dado Transportista_cuando Buscar Por Transportista_entonces ListaDetallesEnvioDtoSend")
    @Test
    public void dadoTransportista_cuandoBuscarPorTransportista_entoncesListaDetalleEnvioDtoSend(){
        when(detalleEnvioRepository.findByTransportadora(detalleEnvio.getTransportadora())).thenReturn(List.of(detalleEnvio));
        when(detalleEnvioMapper.detalleEnvioToDetalleEnvioDtoSend((DetalleEnvio)List.of(detalleEnvio))).thenReturn(detalleEnvioDtoSend);

        List<DetalleEnvioDtoSend>detalleEnvioDtoSendList = detalleEnvioService.ObtenerDetalleDeEnvioPorTransportadora(detalleEnvio.getTransportadora());
        assertEquals(List.of(detalleEnvioDtoSend), detalleEnvioDtoSendList);
        assertEquals(1, detalleEnvioDtoSendList.size());
        assertEquals(detalleEnvioDtoSend.getTransportadora(),detalleEnvioDtoSendList.get(0).getTransportadora());
    }
    @DisplayName("dado EstadoOrden_cuando Buscar Por Orden_Status_entonces Lista EnvioDetallesDtoSend")
    @Test
    public void dadoEstadoPedido_cuandoBuscarPorStatusPedido_entoncesListaDetallesEnvioDtoSend(){
        when(detalleEnvioRepository.findByEstadoDePedido(pedido.getStatus())).thenReturn(List.of(detalleEnvio));
        when(detalleEnvioMapper.detalleEnvioToDetalleEnvioDtoSend((DetalleEnvio)List.of(detalleEnvio))).thenReturn(detalleEnvioDtoSend);

        List<DetalleEnvioDtoSend>detalleEnvioDtoSendList =detalleEnvioService.ObtenerDetalleDeEnvioPorEstado(pedido.getStatus());
        assertEquals(List.of(detalleEnvioDtoSend), detalleEnvioDtoSendList);
        assertEquals(1, detalleEnvioDtoSendList.size());
        assertEquals(detalleEnvio.getPedido().getStatus(),pedido.getStatus());
    }
*/
}

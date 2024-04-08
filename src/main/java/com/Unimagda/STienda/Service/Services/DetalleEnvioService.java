package com.Unimagda.STienda.Service.Services;

import com.Unimagda.STienda.DTO.Send.DetalleEnvioDtoSend;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;

import java.util.List;
public interface DetalleEnvioService {

     List<DetalleEnvioDtoSend>ObtenerDetalleEnvioPorId(Long idPedido);
     List<DetalleEnvioDtoSend>ObtenerDetalleDeEnvioPorTransportadora(String transportadora);
     List<DetalleEnvioDtoSend> ObtenerDetalleDeEnvioPorEstado(EstadoDePedido estadoDePedido);
     //-----------------------------------------------------------------------

 //  List<DetalleEnvioDtoSend>ObtenerNumeroDeGuiaYEstadoDelPedido(Long NumeroDeGuia, EstadoDePedido estadoDePedido);
DetalleEnvioDtoSend save(DetalleEnvioDtoSend detalleEnvioDtoSend);
DetalleEnvioDtoSend Update(DetalleEnvioDtoSend detalleEnvioDtoSend);
}

package com.Unimagda.STienda.Service;

import com.Unimagda.STienda.DTO.DetalleEnvioDto;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;

import java.util.List;
public interface DetalleEnvioService {

     List<DetalleEnvioDto>ObtenerDetalleEnvioPorId(Long idPedido);
     List<DetalleEnvioDto>ObtenerDetalleDeEnvioPorTransportadora(String transportadora);
     List<DetalleEnvioDto> ObtenerDetalleDeEnvioPorEstado(EstadoDePedido estadoDePedido);

//-----------------------------------------------------------------------

   List<DetalleEnvioDto>ObtenerNumeroDeGuiaYEstadoDelPedido(String NumeroDeGuia, EstadoDePedido estadoDePedido);

}

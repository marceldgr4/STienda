package com.Unimagda.STienda.Service.Services;

import com.Unimagda.STienda.DTO.Save.DetalleEnvioDtoSave;
import com.Unimagda.STienda.DTO.Send.DetalleEnvioDtoSend;
import com.Unimagda.STienda.Entity.DetalleEnvio;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import com.Unimagda.STienda.Service.Service;

import java.util.List;
public interface DetalleEnvioService extends Service<DetalleEnvioDtoSave, DetalleEnvioDtoSend, DetalleEnvio> {

     List<DetalleEnvioDtoSend> findByIdPedido(Long idPedido);
     List<DetalleEnvioDtoSend>ObtenerDetalleDeEnvioPorTransportadora(String transportadora);
     List<DetalleEnvioDtoSend> ObtenerDetalleDeEnvioPorEstado(EstadoDePedido estadoDePedido);
     //-----------------------------------------------------------------------

 //  List<DetalleEnvioDtoSend>ObtenerNumeroDeGuiaYEstadoDelPedido(Long NumeroDeGuia, EstadoDePedido estadoDePedido);
        DetalleEnvioDtoSend save(DetalleEnvioDtoSave detalleEnvioDtoSend, Long idPedido);
        DetalleEnvioDtoSend update(DetalleEnvioDtoSave detalleEnvioDtoSend, Long id);
}

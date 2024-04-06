package com.Unimagda.STienda.Service;

import com.Unimagda.STienda.DTO.PagoDto;
import com.Unimagda.STienda.Entity.Enum.MetodoDePago;
import com.Unimagda.STienda.Entity.Pago;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public interface PagoService  {

     List<PagoDto> ObtenerPagoEnRangoDeFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) ;
     List<PagoDto> ObtenerPagoPorIdPedidoYMetodoDePago(Long idPedido, MetodoDePago metodoDePago) ;
    //---------------------------------------------------------------------------
    //----Obtener otros datos-----------
     List<PagoDto> ObtenerPagoPorIdCliente(Long idCliente) ;
     List<PagoDto>ObtenerTodo();
     List<PagoDto> ObtenerIdPago(Long idPago);
    //------------------------CRUD-----------------------
    PagoDto CrearPago(PagoDto pagoDto) ;
    Pago ActualizarPago(Long idPago, Pago pago) ;
    Boolean EliminarPago(Long idPago) ;
}

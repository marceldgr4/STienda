package com.Unimagda.STienda.Service.Services;
import com.Unimagda.STienda.DTO.Save.PagoDtoSave;
import com.Unimagda.STienda.DTO.Send.PagoDtoSend;
import com.Unimagda.STienda.Entity.Enum.MetodoDePago;
import com.Unimagda.STienda.Entity.Pago;

import com.Unimagda.STienda.Service.Service;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface PagoService extends Service< PagoDtoSave, PagoDtoSend,Pago> {
    Page<PagoDtoSend> BuscarPorRangoDeFecha(LocalDateTime fechaInicial, LocalDateTime fechaFinal);
    Page<PagoDtoSend> buscarMetodoDePagoYPedido_IDPedido(MetodoDePago metodoDePago,Long idPedido);
    PagoDtoSend save(PagoDtoSave pagoDtoSave, Long idPedido);
    PagoDtoSend Update(PagoDtoSave pagoDtoSave, Long id);


}

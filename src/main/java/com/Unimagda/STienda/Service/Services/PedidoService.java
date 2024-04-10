package com.Unimagda.STienda.Service.Services;


import com.Unimagda.STienda.DTO.Save.PedidoDtoSave;
import com.Unimagda.STienda.DTO.Send.PedidoDtoSend;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import com.Unimagda.STienda.Entity.Pedido;
import com.Unimagda.STienda.Service.Service;
import org.springframework.data.domain.Page;


import java.time.LocalDateTime;

public interface PedidoService extends Service<PedidoDtoSave, PedidoDtoSend, Pedido> {
    Page<PedidoDtoSend> ObtenerPedidoEntreDosFechas(LocalDateTime fichaInicio, LocalDateTime fichaFin);
    Page<PedidoDtoSend> ObtenerClienteYEstado(Long idCliente, EstadoDePedido estadoDePedido);
    Page<PedidoDtoSend> ObtenerClientePorItem(Long idCliente);
    PedidoDtoSend Update(PedidoDtoSave pedidoDtoSave, Long id);
    PedidoDtoSend save(PedidoDtoSave pedidoDtoSave, Long idCliente);
}

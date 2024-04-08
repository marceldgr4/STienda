package com.Unimagda.STienda.Service.Services;

import com.Unimagda.STienda.DTO.Dto.PedidoDto;
import com.Unimagda.STienda.Entity.Cliente;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;


import java.time.LocalDateTime;
import java.util.List;

public interface PedidoService {
    List<PedidoDto> ObtenerPedidoEntreDosFechas(LocalDateTime fichaInicio, LocalDateTime fichaFin);
    List<PedidoDto> ObtenerClienteYEstado(Cliente cliente, EstadoDePedido estadoDePedido);
    List<PedidoDto> ObtenerPedidoConProductos(Long idCliente);
}

package com.Unimagda.STienda.DTO.Send;

import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDtoSend {
    private Long idPedido;
    private Long idCliente;
    private LocalDateTime fechaPedido;
    private EstadoDePedido estadoDePedido;

    List<ItemPedidoDtoSend> ItemPedido;
    private DetalleEnvioDtoSend detalleEnvio;
    private PagoDtoSend pago;
}

package com.Unimagda.STienda.DTO.Send;

import com.Unimagda.STienda.DTO.Save.ItemPedidoDtoSave;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemPedidoDtoSend  {
    private Long idItemPedido;
    private int Cantidad;
    private Double PrecioUnitario;
    private Long idPedido;
    private Long idProducto;
}

package com.Unimagda.STienda.DTO.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ItemPedidoDto {
    private Long idItemPedido;
    private Long idPedido;
    private Long idProducto;
    private int Cantidad;
    private Double PrecioUnitario;

}

package com.Unimagda.STienda.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ItemPedidoDto {
    private Long id;
    private Long idPedido;
    private Long idProducto;
    private int Cantidad;
    private Double PrecioUnitario;

}

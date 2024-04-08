package com.Unimagda.STienda.DTO.Dto;

import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class PedidoDto {
    private Long idPedido;
    private LocalDateTime FechaDePedido;
    private EstadoDePedido Status;
}

package com.Unimagda.STienda.DTO;

import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class PedidoDto {
    private Long id;
    private LocalDateTime FechaDePedido;
    private EstadoDePedido Status;
}

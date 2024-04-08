package com.Unimagda.STienda.DTO.Save;

import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDtoSave {
    private EstadoDePedido estadoDePedido;
}

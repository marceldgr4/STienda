package com.Unimagda.STienda.DTO.Save;

import com.Unimagda.STienda.DTO.Send.ItemPedidoDtoSend;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemPedidoDtoSave  {
    @Min(1)
    private int Cantidad;
    private Double PrecioUnitario;
}

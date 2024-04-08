package com.Unimagda.STienda.DTO.Send;

import com.Unimagda.STienda.Entity.Enum.MetodoDePago;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagoDtoSend {
    private Long idPago;
    private Long idPedido;
    private Double PagoTotal;
    private LocalDate fechaPago;
    private MetodoDePago metodoDePago;

}

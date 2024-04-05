package com.Unimagda.STienda.DTO;

import com.Unimagda.STienda.Entity.Enum.MetodoDePago;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class PagoDto {
    private Long id;
    private Long idCliente;
    private Double TotalDePago;
    private LocalDateTime FechaDePago;
    private MetodoDePago metodoDePago;
}

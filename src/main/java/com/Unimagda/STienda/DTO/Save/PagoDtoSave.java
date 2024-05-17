package com.Unimagda.STienda.DTO.Save;

import com.Unimagda.STienda.Entity.Enum.MetodoDePago;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagoDtoSave {
    private  Double PagoTotal;
    @JsonFormat(pattern = "dd/mm/yyyy",timezone = "GMT-5")
    private LocalDate fechaPago;
    private MetodoDePago metodoDePago;
}

package com.Unimagda.STienda.DTO.Save;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleEnvioDtoSave {
    private Long idPedido;
    private String Direccion;
    private String Transportadora;
    private Integer NumeroDeGuia;
}

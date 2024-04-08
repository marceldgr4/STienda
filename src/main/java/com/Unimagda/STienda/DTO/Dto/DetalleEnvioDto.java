package com.Unimagda.STienda.DTO.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class DetalleEnvioDto {
    private Long idDetalleEnvio;
    private Long idPedido;
    private  String Direccion;
    private String Transportadora;
    private String NumeroDeGuia;
}

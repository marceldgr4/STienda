package com.Unimagda.STienda.DTO.Send;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleEnvioDtoSend {
    private Long idDetalleEnvio;
    private Long idPedido;
    private  String Direccion;
    private String Transportadora;
    private Long NumeroDeGuia;
}

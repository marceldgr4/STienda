package com.Unimagda.STienda.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class DetalleEnvioDto {
    private Long id;
    private Long idPedido;
    private  String Direccion;
    private String Transportadora;
    private String NumeroDeGuia;
}

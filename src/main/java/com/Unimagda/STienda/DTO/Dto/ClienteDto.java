package com.Unimagda.STienda.DTO.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class ClienteDto {
    private Long id;
    private String Nombre;
    private String Email;
    private String Direccion;
    private  String CityName;
}

package com.Unimagda.STienda.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ClienteDto {
    private Long id;
    private String Nombre;
    private String Email;
    private String Direccion;
    private  String CityName;
}

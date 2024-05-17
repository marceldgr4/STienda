package com.Unimagda.STienda.DTO.Save;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ClienteDtoSave {
@NotBlank
    private String Nombre;
@Email
    private String Email;
    private String Direccion;
    private String CityName;

}

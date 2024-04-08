package com.Unimagda.STienda.DTO.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ProductoDto {
    private Long idProducto ;
    private String NombreProducto;
    private  Double PrecioProducto;
    private Integer Stock;
}

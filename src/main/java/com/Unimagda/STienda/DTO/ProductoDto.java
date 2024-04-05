package com.Unimagda.STienda.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

public class ProductoDto {
    private Long id;
    private String NombreProducto;
    private  Double PrecioProducto;
    private Integer Stock;
}

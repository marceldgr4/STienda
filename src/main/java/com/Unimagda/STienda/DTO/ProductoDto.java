package com.Unimagda.STienda.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ProductoDto {
    private Long id;
    private String NombreProducto;
    private  Double PrecioProducto;
    private Integer Stock;
}

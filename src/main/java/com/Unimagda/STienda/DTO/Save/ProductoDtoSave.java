package com.Unimagda.STienda.DTO.Save;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDtoSave {
    @NotBlank
    private String nombreProducto;
    @Min(0)
    private double precioProducto;
    @Min(0)
    private Integer Stock;
}

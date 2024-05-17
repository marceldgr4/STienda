package com.Unimagda.STienda.DTO.Send;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDtoSend {
    private Long idProducto;
    private String NombreProducto;
    private Double PrecioProducto;
    private Integer Stock;
     @JsonIgnore
    private List<ItemPedidoDtoSend> itemPedido;
}

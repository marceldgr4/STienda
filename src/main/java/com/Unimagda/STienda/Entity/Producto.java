package com.Unimagda.STienda.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@Entity
@Table(name= "productos")
@AllArgsConstructor
@NoArgsConstructor

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    private String NombreProducto;
    private Double PrecioProducto;
    private Integer Stock;
    @OneToMany(mappedBy = "producto")
   private List<ItemPedido> itemPedidos;

    public Producto UpdateProducto(Producto producto) {
        return new Producto(this.idProducto, producto.NombreProducto,producto.PrecioProducto,producto.Stock,producto.itemPedidos);
    }


}

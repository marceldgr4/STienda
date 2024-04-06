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
    private Long id;
    private String NombreProducto;
    private Double PrecioProducto;
    private int Stock;
    @OneToMany(mappedBy = "producto")
   private List<ItemPedido> itemPedidos;


}

package com.Unimagda.STienda.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

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
    private Integer Stock;
    @OneToMany(mappedBy = "producto")
    private List<ItemPedido> itemPedidos
}

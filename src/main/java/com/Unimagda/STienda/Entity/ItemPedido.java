package com.Unimagda.STienda.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "itemPedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemPedido;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    private int Cantidad;
    private Double PrecioUnitario;


}

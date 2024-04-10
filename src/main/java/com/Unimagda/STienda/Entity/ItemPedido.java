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
@Builder
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemPedido;
    private int Cantidad;
    private Double PrecioUnitario;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;



    public ItemPedido itemPedidoUpdate(ItemPedido itemPedido) {
        Producto producto = itemPedido.getProducto() !=null ? itemPedido.getProducto() : this.producto;
        Pedido UpdatePedido = itemPedido.getPedido() !=null ? itemPedido.getPedido() : this.pedido;
        return new ItemPedido (this.idItemPedido, itemPedido.Cantidad, itemPedido.PrecioUnitario,  UpdatePedido, producto);

    }

}

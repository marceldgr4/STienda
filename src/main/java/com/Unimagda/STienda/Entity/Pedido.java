package com.Unimagda.STienda.Entity;

import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    private LocalDateTime FechaPedido;
    private Double Total;


    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedidos")
    private List<ItemPedido>itemPedidos;

    @OneToOne(mappedBy = "pedido")
    private Pago pago;

    @OneToOne(mappedBy = "pedido")
    private DetalleEnvio detalleEnvio;

    @Enumerated(EnumType.STRING)
    private EstadoDePedido estadoDePedido;

    public Pedido UpdatePedido(Pedido pedido){
        Cliente updateCliente = pedido.getCliente() != null? pedido.getCliente(): this.cliente;
        return new Pedido(this.idPedido, LocalDateTime.now(),
                pedido.Total,
                updateCliente,
                pedido.itemPedidos,
                pedido.pago,
                pedido.detalleEnvio,
                pedido.estadoDePedido);

    }






}

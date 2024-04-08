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

    @OneToMany(mappedBy = "pedidos")
    private List<ItemPedido>itemPedidos;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    private LocalDateTime FechaPedido;
    private Double Total;

    @Enumerated(EnumType.STRING)
    private EstadoDePedido Status;

    @OneToOne(mappedBy = "pedido")
    private Pago pago;

    @OneToOne(mappedBy = "pedido")
    private DetalleEnvio detalleEnvio;





}

package com.Unimagda.STienda.Entity;


import com.Unimagda.STienda.Entity.Enum.MetodoDePago;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "pagos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;
    private Double TotalAPagar;
    private LocalDateTime FechaDePago;
    private MetodoDePago metodoDePago;

    @OneToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;



    @Enumerated(EnumType.STRING)


    public Pago pagoUpdate(Pago pago) {
        Pedido PedidoUpdate = pago.getPedido() !=null ? pago.getPedido() : this.pedido;
        return new Pago(this.idPago, pago.TotalAPagar, pago.FechaDePago,pago.metodoDePago, PedidoUpdate);
    }
}

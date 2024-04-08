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

    @OneToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    private Double TotalAPagar;
    private LocalDateTime FechaDePago;

    @Enumerated(EnumType.STRING)
    private MetodoDePago metodoDePago;

}

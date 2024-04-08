package com.Unimagda.STienda.Entity;

import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalleEnvios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DetalleEnvio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleEnvio;
    private String Direccion;
    private String Transportadora;
    private  Long NumeroDeGuia;
    private EstadoDePedido EstadoDePedido;

    @OneToOne
    @JoinColumn(name = "idPedido")
    private Pedido  pedido;
}

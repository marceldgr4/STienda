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
    @Column(nullable = false)
    private String Direccion;
    @Column(nullable = false)
    private String Transportadora;
    @Column(nullable = false)
    private  Long NumeroDeGuia;
    @Column(nullable = false)
    private EstadoDePedido estadoDePedido;

    @OneToOne
    @JoinColumn(name = "idPedido")
    private Pedido  pedido;

    public DetalleEnvio detalleEnvioUpdate(DetalleEnvio detalleEnvio){
        Pedido PedidoUpdate = getPedido() !=null? getPedido(): detalleEnvio.getPedido();
        return  new DetalleEnvio(this.idDetalleEnvio,
                detalleEnvio.Direccion,
                detalleEnvio.Transportadora,
                detalleEnvio.NumeroDeGuia,
                detalleEnvio.estadoDePedido,
                PedidoUpdate);
    }
}

package com.Unimagda.STienda.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "clientes")
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(nullable = false)
    private String Nombre;

    @Column(unique = true)
    private String Email;

    @Column(nullable = false)
    private String Direccion;
    @Column(nullable = false)

    private String CityName;
    @Column(nullable = false)

    @OneToMany(mappedBy = "cliente")
    private List<Pedido>pedidos;

   public Cliente ActualizarCliente(Cliente cliente) {
       return new Cliente(this.idCliente, cliente.Nombre, cliente.Email, cliente.Direccion,cliente.CityName,cliente.pedidos);
   }


}

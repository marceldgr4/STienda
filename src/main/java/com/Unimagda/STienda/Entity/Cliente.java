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
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Nombre;
    private String Email;
    private String Direccion;
    private String CityName;
    @OneToMany(mappedBy = "cliente")
    private List<Pedido>pedidos;

}

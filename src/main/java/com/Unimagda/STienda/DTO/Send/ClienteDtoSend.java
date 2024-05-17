package com.Unimagda.STienda.DTO.Send;

import com.Unimagda.STienda.Entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDtoSend {
    private Long idCliente;
    private String Nombre;
    private String Email;
    private String Direccion;
    private  String CityName;
    private List<Pedido> pedidos;
}

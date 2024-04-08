package com.Unimagda.STienda.Repository.Repositorys;

import com.Unimagda.STienda.Entity.DetalleEnvio;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;

import com.Unimagda.STienda.Repository.Repository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface DetalleEnvioRepository extends Repository<DetalleEnvio> {
    List<DetalleEnvio> findByPedidoId(Long idPedido);

    List<DetalleEnvio> findByTransportadora(String transportadora);
    List<DetalleEnvio> findByEstado(EstadoDePedido estado);

    @Query("SELECT DE FROM DetalleEnvio DE WHERE DE.EstadoDePedido =: EstadoDePedido")
   List<DetalleEnvio> findByEstadoDePedido(EstadoDePedido EstadoDePedido);



}

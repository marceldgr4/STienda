package com.Unimagda.STienda.Repository.Repositorys;

import com.Unimagda.STienda.Entity.DetalleEnvio;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;

import com.Unimagda.STienda.Repository.Repository;



import java.util.List;


public interface DetalleEnvioRepository extends Repository<DetalleEnvio>, org.aspectj.apache.bcel.util.Repository {
    List<DetalleEnvio> findByPedido_IdPedido(Long idPedido);

    List<DetalleEnvio> findByTransportadora(String transportadora);
    List<DetalleEnvio> findByEstadoDePedido(EstadoDePedido estado);

   /* @Query("SELECT DE FROM DetalleEnvio DE WHERE DE.EstadoDePedido =: EstadoDePedido")
   List<DetalleEnvio> findByEstadoDePedido(EstadoDePedido EstadoDePedido);
    */


}

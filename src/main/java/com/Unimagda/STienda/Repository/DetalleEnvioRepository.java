package com.Unimagda.STienda.Repository;

import com.Unimagda.STienda.Entity.DetalleEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleEnvioRepository extends JpaRepository<DetalleEnvio, Long> {
    List<DetalleEnvio> findByPedidoId(Long idPedido);
    List<DetalleEnvio> findByTransportadora(String transportadora);

    @Query("SELECT DE FROM DetalleEnvio DE WHERE DE.EstadoDePedido =: EstadoDePedido")
    List<DetalleEnvio>findByEstadoDePedido(String EstadoDePedido);
}

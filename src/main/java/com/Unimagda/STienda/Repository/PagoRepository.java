package com.Unimagda.STienda.Repository;

import com.Unimagda.STienda.Entity.Enum.MetodoDePago;
import com.Unimagda.STienda.Entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago>findByFechaDePagoBetween(LocalDateTime fechaInicial, LocalDateTime fechaFinal);
    List<Pago> findByPedidoIdAndMetodoDePago(Long idPedido, MetodoDePago metodoDePago);
}

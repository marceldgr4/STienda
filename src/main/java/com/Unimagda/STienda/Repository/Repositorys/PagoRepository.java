package com.Unimagda.STienda.Repository.Repositorys;

import com.Unimagda.STienda.Entity.Enum.MetodoDePago;
import com.Unimagda.STienda.Entity.Pago;
import com.Unimagda.STienda.Repository.Repository;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;
import java.util.List;


public interface PagoRepository extends Repository<Pago> {
    List<Pago>findByFechaDePagoBetween(Pageable pageable, LocalDateTime fechaInicial, LocalDateTime fechaFinal);
    List<Pago> findByPedidoIdAndMetodoDePago(Pageable pageable,Long idPedido, MetodoDePago metodoDePago);
}

package com.Unimagda.STienda.Repository.Repositorys;

import com.Unimagda.STienda.Entity.Enum.MetodoDePago;
import com.Unimagda.STienda.Entity.Pago;
import com.Unimagda.STienda.Repository.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;



public interface PagoRepository extends Repository<Pago>, org.aspectj.apache.bcel.util.Repository {
    Page<Pago>findByFechaDePagoBetween(Pageable pageable, LocalDateTime fechaInicial, LocalDateTime fechaFinal);
    Page<Pago> findByPedidoIdAndMetodoDePago(Pageable pageable,Long idPedido, MetodoDePago metodoDePago);

}

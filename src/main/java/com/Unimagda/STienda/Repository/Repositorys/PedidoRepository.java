package com.Unimagda.STienda.Repository.Repositorys;


import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import com.Unimagda.STienda.Entity.Pedido;
import com.Unimagda.STienda.Repository.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import java.time.LocalDateTime;
import java.util.List;


public interface PedidoRepository extends Repository<Pedido> {
    Page<Pedido> findByFechaPedidoBetween(Pageable pageable, LocalDateTime fechaInicial, LocalDateTime fechaFinal);
    Page<Pedido> findByClienteAndStatus(Pageable pageable, Long idCliente, EstadoDePedido estadoDePedido);
   @Query("SELECT DISTINCT P FROM Pedido P JOIN FETCH P.itemPedidos WHERE P.cliente.idCliente = :cliente")
    Page<Pedido>findByClienteWithItemsPedido(Pageable pageable, @Param("cliente")Long idCliente);

   @EntityGraph(attributePaths = {"items"})
    List<Pedido>findByClienteId(Long idCliente);
}

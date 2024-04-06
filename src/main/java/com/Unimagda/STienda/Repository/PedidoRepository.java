package com.Unimagda.STienda.Repository;

import com.Unimagda.STienda.Entity.Cliente;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import com.Unimagda.STienda.Entity.Pedido;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido>findByFechaPedidoBetween(LocalDateTime fechaInicial, LocalDateTime fechaFinal);
    List<Pedido> findByClienteAndStatus(Cliente cliente, EstadoDePedido estadoDePedido);
   @Query("SELECT DISTINCT P FROM Pedido P JOIN FETCH P.itemPedidos WHERE P.cliente = :cliente")
    List<Pedido>findByClienteWithItemsPedido(@Param("cliente")Cliente cliente);

   @EntityGraph(attributePaths = {"items"})
    List<Pedido>findByClienteId(Long idCliente);
}

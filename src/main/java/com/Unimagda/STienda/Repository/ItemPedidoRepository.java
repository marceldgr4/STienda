package com.Unimagda.STienda.Repository;

import com.Unimagda.STienda.DTO.Dto.ItemPedidoDto;
import com.Unimagda.STienda.Entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    List<ItemPedido>findByPedidoId(Long idPedido);
    List<ItemPedido>findByProductoId(Long idProducto);

    @Query("SELECT SUM(IP.Cantidad *IP.PrecioUnitario) FROM ItemPedido IP WHERE IP.producto.id=:idProducto")
    List<ItemPedidoDto> CalcularTotalDeVentaPorProducto(@Param("idProducto") Long idProducto);
}

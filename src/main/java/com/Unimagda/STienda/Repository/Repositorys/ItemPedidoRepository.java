package com.Unimagda.STienda.Repository.Repositorys;


import com.Unimagda.STienda.Entity.ItemPedido;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface ItemPedidoRepository extends Repository<ItemPedido, Long> {
    List<ItemPedido>findByIdPedido(Long idPedido);
    List<ItemPedido> findByIdProducto(Long idProducto);

    @Query("SELECT SUM(IP.Cantidad ) FROM ItemPedido IP WHERE IP.producto.idProducto=:idProducto")
    Double SumaTotalDeVentaPorProducto(@Param("idProducto") Long idProducto);
}

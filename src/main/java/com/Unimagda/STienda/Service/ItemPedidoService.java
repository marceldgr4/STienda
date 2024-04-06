package com.Unimagda.STienda.Service;

import com.Unimagda.STienda.DTO.ItemPedidoDto;



import java.util.List;



public interface ItemPedidoService {

    List<ItemPedidoDto> ObtenerItemPedidosPorIdPedido(Long idPedido);
    List<ItemPedidoDto>ObtenerItemPedidosPorProductoEspecifico(Long idProducto) ;
     List<ItemPedidoDto> ObtenerLaSumaTotalDeVentasDeProducto(Long idProducto) ;


}

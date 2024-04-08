package com.Unimagda.STienda.Service;

import com.Unimagda.STienda.DTO.Dto.ItemPedidoDto;



import java.util.List;



public interface ItemPedidoService {

    List<ItemPedidoDto> ObtenerItemPedidosPorIdPedido(Long idPedido);
    List<ItemPedidoDto>ObtenerItemPedidosPorProductoEspecifico(Long idProducto) ;
     List<ItemPedidoDto> ObtenerLaSumaTotalDeVentasDeProducto(Long idProducto) ;


}

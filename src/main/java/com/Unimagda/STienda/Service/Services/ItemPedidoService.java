package com.Unimagda.STienda.Service.Services;


import com.Unimagda.STienda.DTO.Save.ItemPedidoDtoSave;
import com.Unimagda.STienda.DTO.Send.ItemPedidoDtoSend;
import com.Unimagda.STienda.Entity.ItemPedido;
import com.Unimagda.STienda.Service.Service;


import java.util.List;



public interface ItemPedidoService extends Service<ItemPedidoDtoSave, ItemPedidoDtoSend,ItemPedido> {

    List<ItemPedidoDtoSend> ObtenerItemPedidosPorIdPedido(Long idPedido);
    List<ItemPedidoDtoSend>ObtenerItemPedidosPorProductoEspecifico(Long idProducto) ;
    Double ObtenerLaSumaTotalDeVentasDeProducto(Long idProducto) ;
    ItemPedidoDtoSend save(ItemPedidoDtoSave itemPedidoDtoSave, Long idProducto, Long idPedido);
    ItemPedidoDtoSend update(ItemPedidoDtoSave itemPedidoDtoSave, Long id);

}

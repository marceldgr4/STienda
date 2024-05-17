package com.Unimagda.STienda.Mapper.Mappers;


import com.Unimagda.STienda.DTO.Save.ItemPedidoDtoSave;
import com.Unimagda.STienda.DTO.Send.ItemPedidoDtoSend;
import com.Unimagda.STienda.Entity.ItemPedido;
import com.Unimagda.STienda.Mapper.MapperGeneral;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface ItemPedidoMapper extends MapperGeneral<ItemPedidoDtoSave, ItemPedidoDtoSend, ItemPedido> {
    @Mappings({
            @Mapping(source = "pedido.idPedido",target = "idPedido"),
            @Mapping(source = "producto.idProducto",target = "idProducto")
    })
    ItemPedidoDtoSend EntityToDtoSend(ItemPedido itemPedido);

}

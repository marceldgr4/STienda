package com.Unimagda.STienda.Mapper;

import com.Unimagda.STienda.DTO.ItemPedidoDto;
import com.Unimagda.STienda.Entity.ItemPedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {
    ItemPedidoMapper INSTANCE = Mappers.getMapper(ItemPedidoMapper.class);
    @Mapping(target = "id", ignore = true)
    ItemPedidoDto itemPedidoToItemPedidoDto(ItemPedido itemPedido);
    ItemPedido itemPedidoDtoToItemPedido(ItemPedidoDto itemPedidoDto);


}

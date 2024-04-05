package com.Unimagda.STienda.Mapper;

import com.Unimagda.STienda.DTO.PedidoDto;
import com.Unimagda.STienda.Entity.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DetalleEnvioMapper {
    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);
    @Mapping(target = "id", ignore = true)
    PedidoDto pedidoToPedidoDto(Pedido pedido);
    Pedido pedidoDtoToPedido(PedidoDto pedidoDto);
}

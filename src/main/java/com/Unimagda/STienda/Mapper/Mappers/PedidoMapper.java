package com.Unimagda.STienda.Mapper.Mappers;


import com.Unimagda.STienda.DTO.Save.PedidoDtoSave;
import com.Unimagda.STienda.DTO.Send.PedidoDtoSend;
import com.Unimagda.STienda.Entity.Pedido;
import com.Unimagda.STienda.Mapper.MapperGeneral;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface PedidoMapper extends MapperGeneral<PedidoDtoSave,PedidoDtoSend, Pedido> {
@Mapping(source = "cliente.idCliente", target = "idCliente")
    PedidoDtoSend EntityToDtoSend(Pedido pedido);
}

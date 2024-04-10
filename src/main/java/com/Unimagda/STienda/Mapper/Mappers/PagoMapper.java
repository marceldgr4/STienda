package com.Unimagda.STienda.Mapper.Mappers;


import com.Unimagda.STienda.DTO.Save.PagoDtoSave;
import com.Unimagda.STienda.DTO.Send.PagoDtoSend;
import com.Unimagda.STienda.Entity.Pago;
import com.Unimagda.STienda.Mapper.MapperGeneral;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface PagoMapper  extends MapperGeneral<PagoDtoSave, PagoDtoSend, Pago> {
    PagoMapper INSTANCE = Mappers.getMapper(PagoMapper.class);
    @Mapping(source = "pedido.idPedido",target = "idPedido")
    PagoDtoSend EntityToDtoSend(Pago pago);


}

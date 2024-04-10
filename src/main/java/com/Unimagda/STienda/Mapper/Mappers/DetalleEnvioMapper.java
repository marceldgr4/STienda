package com.Unimagda.STienda.Mapper.Mappers;



import com.Unimagda.STienda.DTO.Save.DetalleEnvioDtoSave;
import com.Unimagda.STienda.DTO.Send.DetalleEnvioDtoSend;
import com.Unimagda.STienda.Entity.DetalleEnvio;
import com.Unimagda.STienda.Mapper.MapperGeneral;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface DetalleEnvioMapper  extends MapperGeneral<DetalleEnvioDtoSave, DetalleEnvioDtoSend, DetalleEnvio> {
    @Mapping(source = "Pedido.idPedido",target = "idPedido")
    DetalleEnvioDtoSend EntityToDtoSend(DetalleEnvio entity);
}

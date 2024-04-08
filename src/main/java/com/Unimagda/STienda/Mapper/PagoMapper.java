package com.Unimagda.STienda.Mapper;

import com.Unimagda.STienda.DTO.Dto.PagoDto;
import com.Unimagda.STienda.Entity.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PagoMapper {
    PagoMapper INSTANCE = Mappers.getMapper(PagoMapper.class);
    @Mapping(target = "id", ignore = true)
    PagoDto PagoToPagoDto(Pago pago);
    Pago PagoDtoToPago(PagoDto pago);

}

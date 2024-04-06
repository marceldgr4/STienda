package com.Unimagda.STienda.Mapper;

import com.Unimagda.STienda.DTO.DetalleEnvioDto;

import com.Unimagda.STienda.Entity.DetalleEnvio;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DetalleEnvioMapper {
    DetalleEnvioMapper INSTANCE = Mappers.getMapper(DetalleEnvioMapper.class);
    @Mapping(target = "id", ignore = true)
    DetalleEnvioDto detalleEnvioToDetalleEnvioDto(DetalleEnvio detalleEnvio);
}

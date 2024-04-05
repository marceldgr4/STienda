package com.Unimagda.STienda.Mapper;

import com.Unimagda.STienda.DTO.ProductoDto;
import com.Unimagda.STienda.Entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);
    @Mapping(target = "id", ignore = true)
    ProductoDto productoToProductoDto(Producto producto);
    Producto productoDtoToProducto(ProductoDto productoDto);

}


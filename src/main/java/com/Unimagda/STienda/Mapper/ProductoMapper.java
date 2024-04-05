package com.Unimagda.STienda.Mapper;

import com.Unimagda.STienda.DTO.ProductoDto;
import com.Unimagda.STienda.Entity.Producto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    ProductoDto productoToProducto(Producto producto);
    Producto productoDtoToProducto(ProductoDto productoDto);

}

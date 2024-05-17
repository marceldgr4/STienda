package com.Unimagda.STienda.Mapper.Mappers;

import com.Unimagda.STienda.DTO.Save.ProductoDtoSave;
import com.Unimagda.STienda.DTO.Send.ProductoDtoSend;
import com.Unimagda.STienda.Entity.Producto;
import com.Unimagda.STienda.Mapper.MapperGeneral;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProductoMapper extends MapperGeneral<ProductoDtoSave, ProductoDtoSend, Producto> {

}


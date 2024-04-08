package com.Unimagda.STienda.Mapper.Mappers;


import com.Unimagda.STienda.DTO.Save.ClienteDtoSave;
import com.Unimagda.STienda.DTO.Send.ClienteDtoSend;
import com.Unimagda.STienda.Entity.Cliente;
import com.Unimagda.STienda.Mapper.MapperGeneral;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ClienteMapper extends MapperGeneral<ClienteDtoSave,ClienteDtoSend,Cliente> {
}


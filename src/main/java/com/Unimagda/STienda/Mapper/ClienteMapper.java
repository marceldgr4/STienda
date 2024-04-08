package com.Unimagda.STienda.Mapper;

import com.Unimagda.STienda.DTO.Dto.ClienteDto;
import com.Unimagda.STienda.DTO.Save.ClienteDtoSave;
import com.Unimagda.STienda.DTO.Send.ClienteDtoSend;
import com.Unimagda.STienda.Entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pedido", ignore = true)
    ClienteDto clienteToClienteDto(Cliente cliente);
    Cliente clienteDtoToCliente(ClienteDto clienteDto);

    ClienteDtoSend clientesToClienteDtoSend(Cliente clienteDtoSend);
    ClienteDtoSave clientesToClienteDtoSave(ClienteDtoSave clienteDtoSave);

}


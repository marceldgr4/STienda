package com.Unimagda.STienda.Service;

import com.Unimagda.STienda.DTO.ClienteDto;
import com.Unimagda.STienda.DTO.Send.ClienteDtoSend;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Optional<ClienteDtoSend> BuscarPorEmail(String email) ;
    List<ClienteDto> BuscarPorDireccion(String Direccion);
    List<ClienteDto> BuscarPorNombreDeCiudad(String City);
    List<ClienteDto>BuscarPorNombreConTerm(String Nombre);


//---------------------------------------------------------------------------------

}

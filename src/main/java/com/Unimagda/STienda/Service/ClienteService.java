package com.Unimagda.STienda.Service;

import com.Unimagda.STienda.DTO.ClienteDto;
import java.util.List;
public interface ClienteService {

    List<ClienteDto> BuscarPorEmail(String email) ;
    List<ClienteDto> BuscarPorDireccion(String Direccion);
    List<ClienteDto> BuscarPorNombreDeCiudad(String City);
    List<ClienteDto>BuscarPorNombreConTerm(String Nombre);


//---------------------------------------------------------------------------------

}

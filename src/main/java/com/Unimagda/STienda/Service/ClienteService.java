package com.Unimagda.STienda.Service;

import com.Unimagda.STienda.DTO.Dto.ClienteDto;
import com.Unimagda.STienda.DTO.Save.ClienteDtoSave;
import com.Unimagda.STienda.DTO.Send.ClienteDtoSend;

import java.util.List;


public interface ClienteService {

    List<ClienteDto> BuscarPorEmail(String email) ;
    List<ClienteDtoSend> BuscarPorDireccion(String Direccion);
    List<ClienteDtoSend> BuscarPorNombreDeCiudad(String City);
    List<ClienteDtoSend> BuscarPorNombreConTerm(String Nombre);

    ClienteDto save(ClienteDtoSave clienteDtoSave);
    ClienteDtoSend Update(ClienteDtoSave clienteDtoSave, Long id);


//---------------------------------------------------------------------------------

}

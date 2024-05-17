package com.Unimagda.STienda.Service.Services;


import com.Unimagda.STienda.DTO.Save.ClienteDtoSave;
import com.Unimagda.STienda.DTO.Send.ClienteDtoSend;
import com.Unimagda.STienda.Entity.Cliente;
import com.Unimagda.STienda.Service.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface ClienteService extends Service<ClienteDtoSave,ClienteDtoSend,Cliente> {

    Optional<ClienteDtoSend> findByEmail(String email) ;
    Optional<ClienteDtoSend> findByDireccion(String Direccion);
    Optional<ClienteDtoSend> findByCityName(String City);
    //Page<ClienteDtoSend> BuscarPorNombreConTerm(String Nombre);
    Page<ClienteDtoSend> findByNombreStartingWith(Pageable pageable, String Nombre);
    ClienteDtoSend save(ClienteDtoSave clienteDtoSave);
    ClienteDtoSend Update(ClienteDtoSave clienteDtoSave, Long idCliente);




//---------------------------------------------------------------------------------

}

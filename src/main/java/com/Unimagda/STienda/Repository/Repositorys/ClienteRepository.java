package com.Unimagda.STienda.Repository.Repositorys;


import com.Unimagda.STienda.Entity.Cliente;
import com.Unimagda.STienda.Repository.Repository;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;



public interface ClienteRepository extends Repository<Cliente>, org.aspectj.apache.bcel.util.Repository {
   Cliente findByEmail(String Email);
    Cliente findByDireccion(String Direccion);
    Cliente findByCityName(String CityName);
    Page<Cliente> findByNombreStartingWith(Pageable pageable, String Nombre);


}

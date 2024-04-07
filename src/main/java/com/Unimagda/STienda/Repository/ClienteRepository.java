package com.Unimagda.STienda.Repository;

import com.Unimagda.STienda.Entity.Cliente;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente>findByEmail(String Email);
    List<Cliente>findByDireccion(String Direccion);
    List<Cliente>findByNombreStartingWith(PageRequest pageRequest, String Nombre);
    List<Cliente>findByCityName(String CityName);


    //-------------------------------------------

}

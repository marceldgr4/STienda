package com.Unimagda.STienda.Service.Implements;

import com.Unimagda.STienda.DTO.ClienteDto;
import com.Unimagda.STienda.Mapper.ClienteMapper;
import com.Unimagda.STienda.Repository.ClienteRepository;
import com.Unimagda.STienda.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }
    @Override
    public List<ClienteDto> BuscarPorEmail(String email) {
        return clienteRepository.findByEmail(email)
                .stream().map(clienteMapper::clienteToClienteDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<ClienteDto> BuscarPorDireccion(String Direccion){
        return clienteRepository.findByDireccion(Direccion)
                .stream().map(clienteMapper::clienteToClienteDto)
                .collect(Collectors.toList());

    }
    @Override
    public List<ClienteDto> BuscarPorNombreDeCiudad(String City){
        return clienteRepository.findByCityName(City)
                .stream()
                .map(clienteMapper::clienteToClienteDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<ClienteDto>BuscarPorNombreConTerm(String Nombre){
        return clienteRepository.findByNombreStartingWith(Nombre)
                .stream()
                .map(clienteMapper::clienteToClienteDto)
                .collect(Collectors.toList());
    }
}

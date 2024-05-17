package com.Unimagda.STienda.Service.Implements;


import com.Unimagda.STienda.DTO.Save.ClienteDtoSave;
import com.Unimagda.STienda.DTO.Send.ClienteDtoSend;
import com.Unimagda.STienda.Entity.Cliente;
import com.Unimagda.STienda.Mapper.Mappers.ClienteMapper;

import com.Unimagda.STienda.Repository.Repositorys.ClienteRepository;
import com.Unimagda.STienda.Service.ServiceImpl;
import com.Unimagda.STienda.Service.Services.ClienteService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public  class ClienteServiceImpl extends ServiceImpl<ClienteDtoSave,ClienteDtoSend,Cliente> implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    protected ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        super(clienteRepository,clienteMapper);

        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }
    @Override
    public Optional<ClienteDtoSend>findByEmail(String email){
        Cliente cliente = clienteRepository.findByEmail(email);
        return Optional.ofNullable(clienteMapper.EntityToDtoSend(cliente));
    }
    @Override
    public Optional<ClienteDtoSend>findByDireccion(String Direccion){
        return Optional.ofNullable(clienteMapper.EntityToDtoSend(clienteRepository.findByDireccion(Direccion)));
    }

    @Override
    public Optional<ClienteDtoSend> findByCityName(String City) {
        return Optional.ofNullable(clienteMapper.EntityToDtoSend(clienteRepository.findByCityName(City)));
    }

    @Override
    public Page<ClienteDtoSend> findByNombreStartingWith(Pageable pageable, String Nombre) {
        Pageable pageable1 = PageRequest.of(0,10);
        Page<Cliente>listCliente=clienteRepository.findByNombreStartingWith(pageable1,Nombre);
        return listCliente.map(clienteMapper::EntityToDtoSend);
    }
    @Override
    public ClienteDtoSend Update(ClienteDtoSave clienteDtoSave,Long idCliente){
    Optional<Cliente> cliente=clienteRepository.findById(idCliente);
    if(cliente.isEmpty()){
        return clienteMapper.EntityToDtoSend(clienteRepository.save(clienteMapper.dtoSaveToEntity(clienteDtoSave)));
    }
    Cliente clienteUpDate= cliente.get().ActualizarCliente(clienteMapper.dtoSaveToEntity(clienteDtoSave));
    return clienteMapper.EntityToDtoSend(clienteUpDate);
    }



}

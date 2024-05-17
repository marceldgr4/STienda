package com.Unimagda.STienda.ServiceTest;

import com.Unimagda.STienda.DTO.Save.ClienteDtoSave;
import com.Unimagda.STienda.DTO.Send.ClienteDtoSend;
import com.Unimagda.STienda.Entity.Cliente;
import com.Unimagda.STienda.Mapper.Mappers.ClienteMapper;
import com.Unimagda.STienda.Repository.Repositorys.ClienteRepository;
import com.Unimagda.STienda.Service.Implements.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private ClienteMapper clienteMapper;
    @InjectMocks
    private ClienteServiceImpl clienteServiceImp;
    Cliente cliente;
    ClienteDtoSend clienteDtoSend;
    ClienteDtoSave clienteDtoSave;

    private Page<Cliente> crearPaginaConSoloProducto(Cliente cliente){
        List<Cliente> clienteList = new ArrayList<>();
        clienteList.add(cliente);
        return new PageImpl<>(clienteList);
    }
    @BeforeEach
    public void setUp(){
        cliente = cliente.builder()
                .idCliente(1L)
                .Nombre("cliente3")
                .Email("clien3@gmail.comn")
                .Direccion("direccion3")
                .CityName("ciudad3")
                .build();
        clienteDtoSend = clienteDtoSend.builder()
                .idCliente(1L)
                .Nombre("cliente3")
                .Email("clien3@gmail.comn")
                .Direccion("direccion3")
                .CityName("ciudad3")
                .pedidos(new ArrayList<>())
                .build();
        clienteDtoSave = clienteDtoSave.builder()
                .Nombre("cliente3")
                .Email("clien3@gmail.comn")
                .Direccion("direccion3")
                .CityName("ciudad3")
                .build();

    }
    @Test
    void dadoEmail_cuandoBuscarPorEmail_regresarClienteDtoSend(){
        String email = "clien3@gmail.comn";
        when(clienteRepository.findByEmail(email)).thenReturn(cliente);
        when(clienteMapper.EntityToDtoSend(cliente)).thenReturn(clienteDtoSend);
        Optional<ClienteDtoSend> clienteDtoSendOptional = clienteServiceImp.findByEmail(email);
        assertTrue(clienteDtoSendOptional.isPresent());
        assertEquals(clienteDtoSend,clienteDtoSendOptional.get());
        assertEquals(clienteDtoSendOptional.get().getEmail(),cliente.getEmail());
    }
    @Test
    void dadoDireccion_cuandoBuscarPorDireccion_regresarClienteDtoSend(){
        String direccion = "direccion3";
        when(clienteRepository.findByDireccion(direccion)).thenReturn(cliente);
        when(clienteMapper.EntityToDtoSend(cliente)).thenReturn(clienteDtoSend);
        Optional<ClienteDtoSend> clienteDtoSendOptional = clienteServiceImp.findByDireccion(direccion);
        assertTrue(clienteDtoSendOptional.isPresent());
        assertEquals(clienteDtoSend,clienteDtoSendOptional.get());
        assertEquals(clienteDtoSendOptional.get().getDireccion(),cliente.getDireccion());
    }
    @Test
    void dadoCityName_cuandoBuscarPorNombreDeCiudad_regresarClienteDtoSend(){
        String cityName = "ciudad3";
        when(clienteRepository.findByCityName(cityName)).thenReturn(cliente);
        when(clienteMapper.EntityToDtoSend(cliente)).thenReturn(clienteDtoSend);
        Optional<ClienteDtoSend> clienteDtoSendOptional = clienteServiceImp.findByCityName(cityName);
        assertTrue(clienteDtoSendOptional.isPresent());
        assertEquals(clienteDtoSend,clienteDtoSendOptional.get());
        assertEquals(clienteDtoSendOptional.get().getCityName(),cliente.getCityName());
    }
    @Test
    void dadoNombre_cuandoBuscarPorNombre_regresarClienteDtoSend(){
        String nombre = "cliente3";
        Page<Cliente> page = crearPaginaConSoloProducto(cliente);
        when(clienteMapper.EntityToDtoSend(page.getContent().get(0))).thenReturn(clienteDtoSend);
        when(clienteRepository.findByNombreStartingWith(PageRequest.of(0,10),nombre))
                .thenReturn(page);
        Page<ClienteDtoSend> resultado = clienteServiceImp.findByNombreStartingWith(PageRequest.of(0,10),nombre);
        assertNotNull(resultado);
        assertEquals(resultado.getContent().get(0).getNombre(),clienteDtoSend.getNombre());
    }
    @Test
    void dadoClienteDtoSave_cuandoGuardarClienteDtoSend(){
        when(clienteMapper.dtoSaveToEntity(clienteDtoSave)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteMapper.EntityToDtoSend(cliente)).thenReturn(clienteDtoSend);
        ClienteDtoSend resultado = clienteServiceImp.save(clienteDtoSave);
        assertNotNull(resultado.getIdCliente());
        assertEquals(resultado.getNombre(),cliente.getNombre());
    }

}

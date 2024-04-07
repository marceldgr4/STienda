package com.Unimagda.STienda.Service;

import com.Unimagda.STienda.DTO.ClienteDto;
import com.Unimagda.STienda.DTO.Save.ClienteDtoSave;
import com.Unimagda.STienda.DTO.Send.ClienteDtoSend;
import com.Unimagda.STienda.Entity.Cliente;
import com.Unimagda.STienda.Mapper.ClienteMapper;
import com.Unimagda.STienda.Repository.ClienteRepository;
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
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteService clienteService;

    Cliente cliente;
    ClienteDto clienteDto;
    ClienteDtoSend clienteDtoSend;
    ClienteDtoSave clienteDtoSave;

    private Page<Cliente> CrearPageConProductosUnicos(Cliente cliente) {
        List<Cliente> ListaDeClientes = new ArrayList<>();
        ListaDeClientes.add(cliente);
        return new PageImpl<Cliente>(ListaDeClientes);
    }
    @BeforeEach
    public void setUp() {
        cliente =Cliente.builder()
                .id(1l)
                .Nombre("cliente1")
                .Email("cliente@email.com")
                .Direccion("direccion")
                .CityName("ciudad1")
                .build();
        clienteDtoSend = ClienteDtoSend.builder()
                .id(1l)
                .Nombre("cliente1")
                .Email("cliente@email.com")
                .Direccion("direccion")
                .CityName("ciudad1")
                .pedidos(new ArrayList<>())
                .build();
        clienteDtoSave =ClienteDtoSave.builder()
                .nombre("cliente")
                .email("cliente@example")
                .Direccion("direccion")
                .CityName("ciudad1")
                .build();
    }
    @Test
    void buscarPorEmail() {
        String email = "cliente@email.com";
        when(clienteRepository.findByEmail(email)).thenReturn(Collections.singletonList(cliente));
        when(clienteMapper.clientesToClienteDtoSend(cliente)).thenReturn(clienteDtoSend);
       List<ClienteDto> clienteDtoSend= clienteService.BuscarPorEmail(email);
       assertEquals(1,clienteDtoSend.size());
       assertEquals(clienteDto,clienteDtoSend.get(0));


    }

    @Test
    void buscarPorDireccion() {
        String direccion = "direccion";
        when(clienteRepository.findByDireccion(direccion)).thenReturn(Collections.singletonList(cliente));
        when(clienteMapper.clientesToClienteDtoSend(cliente)).thenReturn(clienteDtoSend);
        List<ClienteDtoSend>clienteDtoSendList=clienteService.BuscarPorDireccion(direccion);
        assertEquals(1,clienteDtoSendList.size());
        assertEquals(clienteDto,clienteDtoSendList.get(0));
    }

    @Test
    void buscarPorNombreDeCiudad() {
        String ciudad = "ciudad1";
        when(clienteRepository.findByCityName(ciudad)).thenReturn(Collections.singletonList(cliente));
        when(clienteMapper.clientesToClienteDtoSend(cliente)).thenReturn(clienteDtoSend);
        List<ClienteDtoSend>clienteDtoSendList =clienteService.BuscarPorNombreDeCiudad(ciudad);
        assertEquals(1,clienteDtoSendList.size());
        assertEquals(clienteDto,clienteDtoSendList.get(0));

    }

    @Test
    void buscarPorNombreConTerm() {
        String nombre = "cliente";
        Page<Cliente>page = CrearPageConProductosUnicos(cliente);
        when(clienteMapper.clientesToClienteDtoSend(page.getContent().get(0))).thenReturn(clienteDtoSend);
        when(clienteRepository.findByNombreStartingWith(PageRequest.of(0,10),nombre)).thenReturn((List<Cliente>) page);

        List<ClienteDtoSend> clienteDtoSendList =clienteService.BuscarPorNombreConTerm(nombre);
        assertEquals(1,clienteDtoSendList.size());
        assertEquals(clienteDto,clienteDtoSendList.get(0));
    }

    @Test
    void save() {
        when(clienteMapper.clientesToClienteDtoSave(clienteDtoSave)).thenReturn(clienteDtoSave); ;
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteMapper.clienteToClienteDto(cliente)).thenReturn(clienteDto);
        ClienteDto ClienteGuardado =clienteService.save(clienteDtoSave);
        assertEquals(clienteDto,ClienteGuardado);
    }
}
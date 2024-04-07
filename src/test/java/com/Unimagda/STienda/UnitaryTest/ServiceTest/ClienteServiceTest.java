package com.Unimagda.STienda.UnitaryTest.ServiceTest;

import com.Unimagda.STienda.DTO.Save.ClienteDtoSave;
import com.Unimagda.STienda.DTO.Send.ClienteDtoSend;
import com.Unimagda.STienda.Entity.Cliente;
import com.Unimagda.STienda.Mapper.ClienteMapper;
import com.Unimagda.STienda.Repository.ClienteRepository;
import com.Unimagda.STienda.Service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static junit.runner.Version.id;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private ClienteMapper clienteMapper;
    @InjectMocks
    private ClienteService clienteService;
    Cliente cliente;
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
                .Direccion("dirrecion")
                .CityName("ciudad1")
                .build();
        clienteDtoSend = ClienteDtoSend.builder()
                .id(1l)
                .Nombre("cliente1")
                .Email("cliente@email.com")
                .Direccion("dirrecion")
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
    void dadoEmail_BuscarPorEmail_Retornar_ClienteDtoSend(){
        String email = "cliente@email.com";
        when(clienteRepository.findByEmail(email)).thenReturn(Collections.singletonList(cliente));
        when(clienteMapper.clientesToClienteDtoSend(cliente)).thenReturn(clienteDtoSend);
        Optional<ClienteDtoSend> clienteDtoSendOptional = clienteService.BuscarPorEmail(email);

        assertTrue(clienteDtoSendOptional.isPresent());
        assertEquals(clienteDtoSend, clienteDtoSendOptional.get());
        assertEquals(clienteDtoSendOptional.get().getEmail(), clienteDtoSend.getEmail());
    }
}

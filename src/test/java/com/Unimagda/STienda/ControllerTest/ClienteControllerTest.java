package com.Unimagda.STienda.ControllerTest;


import com.Unimagda.STienda.DTO.Save.ClienteDtoSave;
import com.Unimagda.STienda.DTO.Send.ClienteDtoSend;

import com.Unimagda.STienda.Service.Services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteControllerTest.class)
@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClienteService clienteService;

    private static final  String URL ="/api/v1/clientes";
    private static final String CONTENT_TYPE = "application/json";

    ClienteDtoSave clienteDtoSave;
    ClienteDtoSend clienteDtoSend;

    @BeforeEach
    void setUp(){
        clienteDtoSave = ClienteDtoSave.builder()
                .Nombre("cliente")
                .Email("cliente@gmail.com")
                .Direccion("direccion")
                .CityName("cuidad")
                .build();
        clienteDtoSend = ClienteDtoSend.builder()
                .idCliente(1L)
                .Nombre("cliente")
                .Email("cliente@gmail.com")
                .Direccion("direccion")
                .CityName("ciudad")
                .pedidos(new ArrayList<>())
                .build();

    }
    @Test
    void getById() throws Exception {
        Long idCliente = 1L;
        when(clienteService.findById(idCliente)).thenReturn(java.util.Optional.of(clienteDtoSend));
        mockMvc.perform(MockMvcRequestBuilders.get(URL+"/"+idCliente)
                .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCliente").value(clienteDtoSend.getIdCliente()))
                .andExpect(jsonPath("$.nombre").value(clienteDtoSend.getNombre()))
                .andExpect(jsonPath("$.email").value(clienteDtoSend.getEmail()))
                .andExpect(jsonPath("$.direccion").value(clienteDtoSend.getDireccion()))
                .andExpect(jsonPath("$.cityName").value(clienteDtoSend.getCityName()))
                .andExpect(jsonPath("$.pedidos").isEmpty());

    }
    @Test
    void getByIdNoFound() throws Exception {
        Long idCliente = 100L;
        when(clienteService.findById(idCliente)).thenThrow(new RuntimeException("No existe el cliente"));
        mockMvc.perform(MockMvcRequestBuilders.get(URL+"/"+idCliente)
                .accept(CONTENT_TYPE)).andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value("No existe el cliente"));
    }
    @Test
    void getAll() throws Exception {
        Page<ClienteDtoSend> clienteDtoSendPage = crearPaginaConSoloProducto(clienteDtoSend);
        when(clienteService.findAll()).thenReturn(clienteDtoSendPage);
        mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(CONTENT_TYPE)).andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1));
    }
    @Test
    void save() throws Exception{
        when(clienteService.save(clienteDtoSave)).thenReturn(clienteDtoSend);
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(CONTENT_TYPE)
                .content(new ObjectMapper().writeValueAsString(clienteDtoSave)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idCliente").value(clienteDtoSend.getIdCliente()));
    }
    @Test
    void update() throws Exception{
        Long idCliente =1L;
        when(clienteService.Update(clienteDtoSave,idCliente)).thenReturn(clienteDtoSend);
        mockMvc.perform(MockMvcRequestBuilders.put(URL+"/"+idCliente)
                .contentType(CONTENT_TYPE)
                .content(new ObjectMapper().writeValueAsString(clienteDtoSave)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCliente").value(clienteDtoSend.getIdCliente()));
    }
    @Test
    void delete() throws Exception{
        long idCliente=1L;
        mockMvc.perform(MockMvcRequestBuilders
                .delete(URL+ "/" +idCliente))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("cliente eliminado"));
    }

    private Page<ClienteDtoSend> crearPaginaConSoloProducto(ClienteDtoSend clienteDtoSend) {
        List<ClienteDtoSend> clienteDtoSendList = new ArrayList<>();
        clienteDtoSendList.add(clienteDtoSend);
        return  new PageImpl<>(clienteDtoSendList);
    }
}

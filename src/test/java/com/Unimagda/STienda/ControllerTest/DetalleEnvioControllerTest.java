package com.Unimagda.STienda.ControllerTest;

import com.Unimagda.STienda.DTO.Save.DetalleEnvioDtoSave;
import com.Unimagda.STienda.DTO.Send.DetalleEnvioDtoSend;
import com.Unimagda.STienda.Service.Services.DetalleEnvioService;
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
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DetalleEnvioControllerTest.class)
@ExtendWith(MockitoExtension.class)
public class DetalleEnvioControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    DetalleEnvioService detalleEnvioService;
    DetalleEnvioDtoSend detalleEnvioDtoSend;
    DetalleEnvioDtoSave detalleEnvioDtoSave;
    private final String URL="/api/v1/Shipping-details";
    private final String CONTENT_TYPE="application/json";

    @BeforeEach
    void setUp() {
        detalleEnvioDtoSave = DetalleEnvioDtoSave.builder()
                .Transportadora("transportadora1")
                .NumeroDeGuia(111222)
                .Direccion("direccion")
                .build();
        detalleEnvioDtoSend = DetalleEnvioDtoSend.builder()
                .idDetalleEnvio(1L)
                .Transportadora("transportadora1")
                .NumeroDeGuia(111222L)
                .Direccion("direccion")
                .idPedido(1L)
                .build();
    }
    @Test
    void getById() throws Exception {
        Long idDetalleEnvio = 1L;
        when(detalleEnvioService.findById(idDetalleEnvio)).thenReturn(Optional.of(detalleEnvioDtoSend));
        mockMvc.perform(MockMvcRequestBuilders
                .get(URL +"/"+ idDetalleEnvio)
                .contentType(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idDetalleEnvio").value(detalleEnvioDtoSend.getIdDetalleEnvio()));

    }
    @Test
    void getByIdNotFound() throws Exception {
        Long idDetalleEnvio = 1L;
        when(detalleEnvioService.findById(idDetalleEnvio)).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
        .get(URL +"/"+ idDetalleEnvio)
                .contentType(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].idDetalleEnvio").value(detalleEnvioDtoSend.getIdDetalleEnvio()));
    }
    @Test
    void getAll() throws Exception {
        Page<DetalleEnvioDtoSend> detalleEnvioDtoSendPage = crearPageWithSingleDetalleEnvio(detalleEnvioDtoSend);
        when(detalleEnvioService.findAll()).thenReturn(detalleEnvioDtoSendPage);
        mockMvc.perform(MockMvcRequestBuilders
        .get(URL)
                .contentType(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].idDetalleEnvio").value(detalleEnvioDtoSend.getIdDetalleEnvio()));

    }
    @Test
    void getByIdPedido() throws Exception {
        Long idPedido = 1L;
        when(detalleEnvioService.ObtenerIdPedido(idPedido)).thenReturn(List.of(detalleEnvioDtoSend));
        mockMvc.perform(MockMvcRequestBuilders
        .get(URL +"/"+ idPedido)
                .contentType(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idDetalleEnvio").value(detalleEnvioDtoSend.getIdDetalleEnvio()))
                .andExpect(jsonPath("$[0].idPedido").value(detalleEnvioDtoSend.getIdPedido()));

    }
    @Test
    void getByIdPedidoNotFound() throws Exception {
        Long idPedido = 1L;
        when(detalleEnvioService.ObtenerIdPedido(idPedido)).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders
        .get(URL +"/"+ idPedido)
                .contentType(CONTENT_TYPE))
                .andExpect(status().isNotFound());
    }
    @Test
    void getByTransportadora() throws Exception {
        String transportadora = "transportadora1";
        when(detalleEnvioService.ObtenerDetalleDeEnvioPorTransportadora(transportadora)).thenReturn(List.of(detalleEnvioDtoSend));
        mockMvc.perform(MockMvcRequestBuilders.get(URL+"/"+ transportadora)
                .contentType(CONTENT_TYPE).param("NombreTransporter",transportadora))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].idDetalleEnvio").value(detalleEnvioDtoSend.getIdDetalleEnvio()))
                .andExpect(jsonPath("$[0].transportadora").value(detalleEnvioDtoSend.getTransportadora()));
    }
    @Test
    void getByTransportadoraNotFound() throws Exception{
        String transportadora = "transportadora1";
        when(detalleEnvioService.ObtenerDetalleDeEnvioPorTransportadora(transportadora)).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders
                        .get(URL+"/"+ transportadora)
                        .contentType(CONTENT_TYPE)
                        .param("NombreTransporter",transportadora))
                        .andExpect(status().isNotFound());
    }
    @Test
    void save() throws Exception{
        Long idPedido= 1L;
        when(detalleEnvioService.save(detalleEnvioDtoSave,idPedido)).thenReturn(detalleEnvioDtoSend);
        mockMvc.perform(MockMvcRequestBuilders
                        .get(URL +"/"+ idPedido)
                        .contentType(CONTENT_TYPE)
                        .content(new ObjectMapper().writeValueAsString(detalleEnvioDtoSave))
                        .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idDetalleEnvio").value(detalleEnvioDtoSend.getIdDetalleEnvio()))
                .andExpect(jsonPath("$[0].idPedido").value(detalleEnvioDtoSend.getIdPedido()));

    }
    @Test
    void Update() throws  Exception{
        Long idDetalleEnvio= 1L;
        when(detalleEnvioService.update(detalleEnvioDtoSave,idDetalleEnvio)).thenReturn(detalleEnvioDtoSend);
        mockMvc.perform(MockMvcRequestBuilders
                        .get(URL +"/"+ idDetalleEnvio)
                        .contentType(CONTENT_TYPE)
                        .content(new ObjectMapper().writeValueAsString(detalleEnvioDtoSave))
                        .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idDetalleEnvio").value(detalleEnvioDtoSend.getIdDetalleEnvio()));
    }
    @Test
    void delete() throws Exception{
        long idDetalleEnvio = 1L;
        mockMvc.perform(MockMvcRequestBuilders
                .get(URL +"/"+ idDetalleEnvio)
                .contentType(CONTENT_TYPE))
                .andExpect(status().isOk());

    }

    private Page<DetalleEnvioDtoSend> crearPageWithSingleDetalleEnvio(DetalleEnvioDtoSend detalleEnvioDtoSend) {
   List<DetalleEnvioDtoSend> detalleEnvioDtoSendList = new ArrayList<>();
   detalleEnvioDtoSendList.add(detalleEnvioDtoSend);
   return new PageImpl<>(detalleEnvioDtoSendList);
    }


}

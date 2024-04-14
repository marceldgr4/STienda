package com.Unimagda.STienda.ControllerTest;

import com.Unimagda.STienda.DTO.Save.ProductoDtoSave;
import com.Unimagda.STienda.DTO.Send.ProductoDtoSend;
import com.Unimagda.STienda.Service.Services.ProductoService;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProductoControllerTest.class)
@ExtendWith(MockitoExtension.class)
public class ProductoControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ProductoService productoService;
    private static final String URL = "/api/v1/producto";
    private static final String CONTENT_TYPE = "application/json";

    ProductoDtoSend productoDtoSend;
    ProductoDtoSave productoDtoSave;
    @BeforeEach
    void setUp(){
        productoDtoSave =productoDtoSave.builder()
                .NombreProducto("product1")
                .PrecioProducto(100.0)
                .Stock(10)
                .build();
        productoDtoSend = productoDtoSend.builder()
                .idProducto(1L)
                .NombreProducto("product1")
                .PrecioProducto(100.0)
                .Stock(10)
                .build();
    }
    @Test
    void getById() throws Exception {
        Long idProducto= 1L;
        when(productoService.findById(idProducto)).thenReturn(Optional.ofNullable(productoDtoSend));
        mockMvc.perform(MockMvcRequestBuilders.get(URL+"/"+idProducto)
                .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProducto").value(productoDtoSend.getIdProducto()));

    }
    @Test
    void getByIdNotFound() throws Exception {
        Long idProducto= 100L;
        when(productoService.findById(idProducto)).thenThrow(new RuntimeException("producto no encontrado"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get(URL+"/"+idProducto)
                        .accept(CONTENT_TYPE))
                        .andExpect(status().isNotFound())
                        .andExpect(content().string("producto no encontrado"));
    }
    @Test
    void getByStockGreaterThanStock() throws Exception {
        Page<ProductoDtoSend> productoDtoSends = crearPageWithSingleProducto(productoDtoSend);
        Integer Stock = 0;
        when(productoService.BuscarEnStock(Stock)).thenReturn(productoDtoSends);
        mockMvc.perform(MockMvcRequestBuilders
                .get(URL+ "/stock/")
                .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].Stock").value(productoDtoSend.getStock()))
                .andExpect(jsonPath("$.content.length()").value(1));
    }
    @Test
    void save() throws Exception {
        when(productoService.save(productoDtoSave)).thenReturn(productoDtoSend);
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
        .contentType(CONTENT_TYPE)
                .content(new ObjectMapper().writeValueAsString(productoDtoSave))
                .accept(CONTENT_TYPE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.NombreProducto").value(productoDtoSave.getNombreProducto()));
    }
    @Test
    void update() throws Exception {
        Long idProducto= 1L;
        when(productoService.Update(productoDtoSave,idProducto)).thenReturn(productoDtoSend);
        mockMvc.perform(MockMvcRequestBuilders.put(URL+"/"+idProducto)
                .contentType(CONTENT_TYPE)
                .content(new ObjectMapper().writeValueAsString(productoDtoSave))
                .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.NombreProducto").value(productoDtoSave.getNombreProducto()));
    }
    @Test
    void delete() throws Exception {
        Long idProducto= 1L;
        doNothing().when(productoService).deleteById(idProducto);
        mockMvc.perform(MockMvcRequestBuilders.delete(URL+"/"+idProducto)
                .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().string("producto eliminado"));
    }

    private Page<ProductoDtoSend> crearPageWithSingleProducto(ProductoDtoSend productoDtoSend) {
    List<ProductoDtoSend> productoDtoSendList = new ArrayList<>();
    productoDtoSendList.add(productoDtoSend);
    return new PageImpl<>(productoDtoSendList);
    }
}

package com.Unimagda.STienda.ServiceTest;

import com.Unimagda.STienda.DTO.Save.ProductoDtoSave;
import com.Unimagda.STienda.DTO.Send.ProductoDtoSend;
import com.Unimagda.STienda.Entity.Producto;
import com.Unimagda.STienda.Mapper.Mappers.ProductoMapper;
import com.Unimagda.STienda.Repository.Repositorys.ProductoRepository;
import com.Unimagda.STienda.Service.Implements.ProductoServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {
    @Mock
    private ProductoRepository productoRepository;
    @Mock
    private ProductoMapper productoMapper;

    @InjectMocks
    private ProductoServiceImpl productoServiceImp;

    Producto producto;
    ProductoDtoSend productoDtoSend;
    ProductoDtoSave productoDtoSave;
    private Page<Producto> crearPageWithSingleProducto(Producto producto) {
        List<Producto> productosList = new ArrayList<>();
        productosList.add(producto);
        return new PageImpl<>(productosList);
    }

    @BeforeEach
    void setUp(){
        producto =producto.builder()
                .idProducto(1L)
                .NombreProducto("producto1")
                .PrecioProducto(20.0)
                .Stock(20)
                .build();
        productoDtoSend = productoDtoSend.builder()
                .idProducto(1L)
                .NombreProducto("producto1")
                .PrecioProducto(20.0)
                .Stock(20)
                .itemPedido(new ArrayList<>())
                .build();
        productoDtoSave = productoDtoSave.builder()
                .NombreProducto("producto1")
                .PrecioProducto(20.0)
                .Stock(20)

                .build();
    }
    @Test
    void dadoNombre_cuandoSeBuscaPorNombre_regresaPageProductoDtoSend() {
        String nombreProducto = "producto1";
        Page<Producto> page = crearPageWithSingleProducto(producto);
        when(productoMapper.EntityToDtoSend(page.getContent().get(0))).thenReturn(productoDtoSend);
        when(productoRepository.findByNombreProducto(PageRequest.of(0,10),nombreProducto)).thenReturn(page);
       Page<ProductoDtoSend> resultado = productoServiceImp.BuscarNombreDeProductos(nombreProducto);
       assertNotNull(resultado);
       assertEquals(resultado.getContent().get(0).getIdProducto(),productoDtoSend.getIdProducto());
    }
    @Test
    void dadoStock_cuandoSeBuscaPorStock_regresaPageProductoDtoSend() {
        Integer stock = 0;
        Page<Producto> page = crearPageWithSingleProducto(producto);
        when(productoRepository.findByStockGreaterThan(PageRequest.of(0,10),stock)).thenReturn(page);
        when(productoMapper.EntityToDtoSend(page.getContent().get(0))).thenReturn(productoDtoSend);
        Page<ProductoDtoSend> resultado = productoServiceImp.BuscarEnStock(stock);
        assertNotNull(resultado);
        assertEquals(resultado.getContent().get(0).getIdProducto(),productoDtoSend.getIdProducto());
    }
    @Test
    void dadoStockYPrecio_cuandoSeBuscaELPrecioYStock_regresaPage_ProductoDtoSend(){
        Integer stock = 0;
        Double precio= 20.0;
        Page<Producto>page = crearPageWithSingleProducto(producto);
        when(productoRepository.findByStockAndPrecioProductoGreaterThan(
                PageRequest.of(0,10),stock,precio)).thenReturn(page);
        when(productoMapper.EntityToDtoSend(page.getContent().get(0))).thenReturn(productoDtoSend);
        Page<ProductoDtoSend> resultado = productoServiceImp.BuscarPorPrecioYStock(stock,precio);
        assertNotNull(resultado);
        assertEquals(resultado.getContent().get(0).getIdProducto(),productoDtoSend.getIdProducto());
    }
    @Test
    void dadoIdProducto_cuando_se_Buscar_ID_regresaProductoDtoSend(){
        Long IdProducto =1L;
        Optional<Producto> optionalProducto = Optional.of(producto);
        when(productoRepository.findById(IdProducto)).thenReturn(optionalProducto);
        when(productoMapper.EntityToDtoSend(producto)).thenReturn(productoDtoSend);
        ProductoDtoSend resultado = productoServiceImp.findById(IdProducto).get();
        assertNotNull(resultado);
        assertEquals(resultado.getIdProducto(),producto.getIdProducto());
    }
    @Test
    void dadoProductoDtoSave_cuandoSe_Actualiza_regresaProductoDtosSend(){
        Long IdProducto = 1L;
        Optional<Producto> optionalProducto = Optional.of(producto);
        when(productoRepository.findById(IdProducto)).thenReturn(optionalProducto);
        when(productoMapper.dtoSaveToEntity(productoDtoSave)).thenReturn(producto);
        when(productoMapper.EntityToDtoSend(producto)).thenReturn(productoDtoSend);
        when(productoRepository.save(producto)).thenReturn(producto);

        ProductoDtoSend resultado = productoServiceImp.Update(productoDtoSave,IdProducto);
        assertNotNull(resultado);
        assertEquals(resultado.getIdProducto(),producto.getIdProducto());
        assertEquals(resultado.getNombreProducto(),producto.getNombreProducto());
    }
    @Test
    void dadoIdProductoNoExistente_cuandoSeActualiza_TheSaveProductoSend(){
        Long idProducto =2L;
        Optional<Producto> optionalProducto = Optional.empty();
        when(productoRepository.findById(idProducto)).thenReturn(optionalProducto);
        when(productoMapper.dtoSaveToEntity(productoDtoSave)).thenReturn(producto);
        when(productoMapper.EntityToDtoSend(producto)).thenReturn(productoDtoSend);
        when(productoRepository.save(producto)).thenReturn(producto);

        ProductoDtoSend resultado = productoServiceImp.Update(productoDtoSave,idProducto);
        assertNotNull(resultado);
        assertEquals(resultado.getIdProducto(),producto.getIdProducto());
        assertEquals(resultado.getNombreProducto(),producto.getNombreProducto());

    }
    @Test
    void dadoProductoDtoSave_cuandoSeGuardad_regresa_ProductoDtoSend(){
        when(productoMapper.dtoSaveToEntity(productoDtoSave)).thenReturn(producto);
        when(productoMapper.EntityToDtoSend(producto)).thenReturn(productoDtoSend);
        when(productoRepository.save(producto)).thenReturn(producto);

        ProductoDtoSend resultado = productoServiceImp.save(productoDtoSave);
        assertNotNull(resultado);
        assertEquals(resultado.getIdProducto(),producto.getIdProducto());
        assertEquals(resultado.getNombreProducto(),producto.getNombreProducto());
    }

}

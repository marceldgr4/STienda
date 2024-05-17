package com.Unimagda.STienda.RepositoryTest;

import com.Unimagda.STienda.AbstractIntegrationBDTest;
import com.Unimagda.STienda.Entity.Producto;
import com.Unimagda.STienda.Repository.Repositorys.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ProductoRepositoryTest extends AbstractIntegrationBDTest {
    private final ProductoRepository productoRepository;
    @Autowired
    ProductoRepositoryTest(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    @BeforeEach
    void setUp() {
        productoRepository.deleteAll();
    }
    void createProducto() {
        Producto producto = Producto.builder()
                .NombreProducto("producto1")
                .Stock(0)
                .Stock(30)
                .PrecioProducto(301.2)
                .build();
        productoRepository.save(producto);
        Producto producto1 = Producto.builder()
                .NombreProducto("producto2")
                .Stock(0)
                .Stock(30)
                .PrecioProducto(301.2)
                .build();
        productoRepository.save(producto1);
        productoRepository.flush();
    }
    @Test
    void dadoProducto_cuandoSeGuardad_IdProducto(){
        createProducto();
        Producto producto = productoRepository.findAll().get(0);
        assertNotNull(producto.getIdProducto());
    }
    @Test
    void dadoNombre_cuandoSeBusca_Nombre_del_Producto_(){
        createProducto();
        Pageable pageable = PageRequest.of(0,10);
        List<Producto> productosList= productoRepository.findAll();
        Producto producto = productoRepository.findByNombreProducto(pageable,"product").getContent()
                .get(0);
        assertNotNull(productosList);
        assertEquals(productosList.get(0).getNombreProducto(),producto.getNombreProducto());

    }
    @Test
    void dadoStock_cuandoSeBusca_Stock_del_Producto_(){
        createProducto();
        Pageable pageable = PageRequest.of(0,10);
        List<Producto> productosList= productoRepository.findAll();
        Producto producto = productoRepository.findByStockGreaterThan(pageable,0).getContent().get(0);
        assertNotNull(producto);
        assertEquals(productosList.get(0).getNombreProducto(),producto.getNombreProducto());
    }
    @Test
    void dadoStock_cuandoSeBusca_Stock_isEmpty(){
        createProducto();
        Pageable pageable = PageRequest.of(0,10);
        List<Producto> productosList= productoRepository.findByStockGreaterThan(pageable,100).getContent();
        assertThat(productosList.isEmpty());
    }
    @Test
    void dadoElPrecioYStock_cuandoSeBusca_PrecioYStock_del_Producto_(){
        createProducto();
        Pageable pageable = PageRequest.of(0,10);
        List<Producto> productosList= productoRepository.findAll();
        Producto producto = productoRepository.findByStockAndPrecioProductoGreaterThan(pageable,30,300.0)
                .getContent().get(0);
        assertNotNull(producto);
        assertEquals(productosList.get(0).getNombreProducto(),producto.getNombreProducto());
    }
    @Test
    void dadoId_cuandoSeBusca_Id_del_Producto(){
        createProducto();
        Producto producto = productoRepository.findAll().get(0);
        Producto productoBuscado= productoRepository.findById(producto.getIdProducto()).get();
        assertNotNull(productoBuscado);
        assertEquals(producto.getIdProducto(),productoBuscado.getIdProducto());

    }
    @Test
    void dadoId_cuandoSeBusca_ID_no_existente_del_Producto(){
        createProducto();
        Optional<Producto> productoBuscado = productoRepository.findById(3L);
        assertNotNull(Optional.empty(), String.valueOf(productoBuscado));
    }
    @Test
    void dadoIDProducto_cuandoSe_Elimina_ID_del_Producto(){
        createProducto();
        Long idProducto =1L;
        productoRepository.deleteById(idProducto);
        assertEquals(1, productoRepository.findAll().size());
        assertThat(productoRepository.findById(idProducto)).isEmpty();
    }
}

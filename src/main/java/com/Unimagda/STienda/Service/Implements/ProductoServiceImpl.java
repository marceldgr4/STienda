package com.Unimagda.STienda.Service.Implements;



import com.Unimagda.STienda.DTO.Save.ProductoDtoSave;
import com.Unimagda.STienda.DTO.Send.ProductoDtoSend;
import com.Unimagda.STienda.Entity.Producto;
import com.Unimagda.STienda.Mapper.Mappers.ProductoMapper;
import com.Unimagda.STienda.Repository.Repositorys.ProductoRepository;
import com.Unimagda.STienda.Service.ServiceImpl;
import com.Unimagda.STienda.Service.Services.ProductoService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductoServiceImpl extends ServiceImpl<ProductoDtoSave, ProductoDtoSend, Producto> implements ProductoService {
private final ProductoRepository productoRepository;
private final ProductoMapper productoMapper;
protected ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper) {
    super(productoRepository, productoMapper);
    this.productoRepository = productoRepository;
    this.productoMapper = productoMapper;
}


    @Override
    public Page<ProductoDtoSend> BuscarNombreDeProductos(String nombre) {
        Pageable pageable = PageRequest.of(0,10);
        Page<Producto> productosList = productoRepository.findByNombreProducto(pageable,nombre);
        return productosList.map(productoMapper::EntityToDtoSend);
    }

    @Override
    public Page<ProductoDtoSend> BuscarEnStock(Integer Stock) {
    Pageable pageable = PageRequest.of(0,10);
    Page<Producto> productosList=productoRepository.findByStockGreaterThan(pageable,Stock);
        return productosList.map(productoMapper::EntityToDtoSend);
    }

    @Override
    public Page<ProductoDtoSend> BuscarPorPrecioYStock(Double PrecioProducto, Integer Stock) {
    Pageable pageable = PageRequest.of(0,10);
    Page<Producto> productosList= productoRepository.findByStockAndPrecioProductoGreaterThan(pageable,PrecioProducto,Stock);
    return productosList.map(productoMapper::EntityToDtoSend);
    }


    @Override
    public ProductoDtoSend Update(ProductoDtoSave productoDtoSave, Long id) {
    Optional<Producto> productos = productoRepository.findById(id);
    if(productos.isEmpty()){
        return productoMapper.EntityToDtoSend(
                productoRepository.save(productoMapper.dtoSaveToEntity(productoDtoSave)));

    }
    Producto productoUpdate = productos.get().UpdateProducto(productoMapper.dtoSaveToEntity(productoDtoSave));
        return productoMapper.EntityToDtoSend(productoRepository.save(productoUpdate));
    }
}

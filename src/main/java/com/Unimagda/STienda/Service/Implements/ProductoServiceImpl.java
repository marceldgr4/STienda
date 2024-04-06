package com.Unimagda.STienda.Service.Implements;

import com.Unimagda.STienda.DTO.ProductoDto;
import com.Unimagda.STienda.Mapper.ProductoMapper;
import com.Unimagda.STienda.Repository.ProductoRepository;
import com.Unimagda.STienda.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    public final ProductoRepository productoRepository;
    public final ProductoMapper productoMapper;

    @Autowired
    public ProductoServiceImpl(ProductoMapper productoMapper, ProductoRepository productoRepository) {
        this.productoMapper = productoMapper;
        this.productoRepository = productoRepository;
    }
    @Override
    public List<ProductoDto> BuscarEnStock(int Stock) {
        return productoRepository.findByStockGreaterThan(0)
                .stream().map(productoMapper::productoToProductoDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<ProductoDto> BuscarNombreDeProductos(String Nombre) {
        return productoRepository.findByNombreProducto().stream()
                .map(productoMapper::productoToProductoDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<ProductoDto>BuscarPorPrecioYStock(Double PrecioProducto, int Stock){
        return productoRepository.findByPrecioAndStockGreaterThan(PrecioProducto,Stock).stream()
                .map(productoMapper::productoToProductoDto)
                .collect(Collectors.toList());
    }
}

package com.Unimagda.STienda.Service;

import com.Unimagda.STienda.DTO.ProductoDto;
import com.Unimagda.STienda.Mapper.ProductoMapper;
import com.Unimagda.STienda.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {
    public final ProductoRepository productoRepository;
    public final ProductoMapper productoMapper;
    @Autowired
    public ProductoService(ProductoMapper productoMapper, ProductoRepository productoRepository) {
        this.productoMapper = productoMapper;
        this.productoRepository = productoRepository;
    }
    public List<ProductoDto> BuscarEnStock() {
        return productoRepository.findByStockGreaterThan(0)
                .stream().map(productoMapper::productoToProductoDto)
                .collect(Collectors.toList());
    }
    public List<ProductoDto> BuscarNombreDeProductos() {
        return productoRepository.findByNombreProducto().stream()
                .map(productoMapper::productoToProductoDto)
                .collect(Collectors.toList());
    }
    public List<ProductoDto>BuscarPorPrecioYStock(Double PrecioProducto, int Stock){
        return productoRepository.findByPrecioAndStockGreaterThan(PrecioProducto,Stock).stream()
                .map(productoMapper::productoToProductoDto)
                .collect(Collectors.toList());
    }
}

package com.Unimagda.STienda.Service;

import com.Unimagda.STienda.DTO.ProductoDto;
import com.Unimagda.STienda.Entity.Producto;
import com.Unimagda.STienda.Mapper.ProductoMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    public final ProductoMapper productoMapper;

    public ProductoService(ProductoMapper productoMapper) {
        this.productoMapper = productoMapper;
    }
    public ProductoDto getProductoDto(Producto producto) {
        return productoMapper.productoToProducto(producto);
    }
}

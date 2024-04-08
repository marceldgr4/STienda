package com.Unimagda.STienda.Service.Services;

import com.Unimagda.STienda.DTO.Dto.ProductoDto;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public interface ProductoService {

    public List<ProductoDto> BuscarEnStock(int Stock);
    public List<ProductoDto> BuscarNombreDeProductos(String nombre); ;
    public List<ProductoDto>BuscarPorPrecioYStock(Double PrecioProducto, int Stock);

}

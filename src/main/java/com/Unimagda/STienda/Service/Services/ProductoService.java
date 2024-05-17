package com.Unimagda.STienda.Service.Services;


import com.Unimagda.STienda.DTO.Save.ProductoDtoSave;
import com.Unimagda.STienda.DTO.Send.ProductoDtoSend;
import com.Unimagda.STienda.Entity.Producto;
import com.Unimagda.STienda.Service.Service;
import org.springframework.data.domain.Page;


public interface ProductoService extends Service<ProductoDtoSave,ProductoDtoSend, Producto> {
   Page<ProductoDtoSend> BuscarNombreDeProductos(String nombre);
   Page<ProductoDtoSend> BuscarEnStock(Integer Stock);
   Page<ProductoDtoSend>BuscarPorPrecioYStock( Integer Stock, Double PrecioProducto);
   ProductoDtoSend Update(ProductoDtoSave productoDtoSave, Long id);

}

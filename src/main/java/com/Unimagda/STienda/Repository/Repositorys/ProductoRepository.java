package com.Unimagda.STienda.Repository.Repositorys;

import com.Unimagda.STienda.Entity.Producto;

import com.Unimagda.STienda.Repository.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;





public interface ProductoRepository extends Repository<Producto> {
    @Query("select p from Producto p where lower(p.NombreProducto) like %:searchTerm%")
    Page<Producto>findByNombreProducto(Pageable pageable, String searchTerm);
    @Query("select p from Producto p where p.Stock > 0")
    Page<Producto> findByStockGreaterThan(Pageable pageable, Integer Stock);

    Page<Producto> findByStockAndPrecioProductoGreaterThan(Pageable pageable,Integer stock,Double precioProducto);

    @Query("select p from Producto p where p.PrecioProducto <=:maxPrecio and p.Stock<=:maxStock")
    Page<Producto>findByPrecioAndStockGreaterThan(Double maxPrecio, Integer maxStock);
}

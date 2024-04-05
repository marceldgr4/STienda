package com.Unimagda.STienda.Repository;

import com.Unimagda.STienda.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query("select p from Producto p where lower(p.NombreProducto) like %:searchTerm%")
    List<Producto> findByNombreContainingIgnoreCase(String term);
    @Query("select p from Producto p where p.Stock > 0")
    List<Producto> findByStockGreaterThan(Integer Cantidad);

    @Query("select p from Producto p where p.PrecioProducto <=:maxPrecio and p.Stock<=:maxStock")
    List<Producto> findByPriceLessThanAndStockLessThanEqual(Double Precio, Integer Stock);
}
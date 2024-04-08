package com.Unimagda.STienda.Service.Implements;

import com.Unimagda.STienda.DTO.Dto.ItemPedidoDto;
import com.Unimagda.STienda.DTO.Save.ItemPedidoDtoSave;
import com.Unimagda.STienda.DTO.Send.ItemPedidoDtoSend;
import com.Unimagda.STienda.Entity.ItemPedido;
import com.Unimagda.STienda.Mapper.Mappers.ItemPedidoMapper;
import com.Unimagda.STienda.Repository.Repositorys.ItemPedidoRepository;
import com.Unimagda.STienda.Repository.Repositorys.PedidoRepository;
import com.Unimagda.STienda.Repository.Repositorys.ProductoRepository;
import com.Unimagda.STienda.Service.ServiceImpl;
import com.Unimagda.STienda.Service.Services.ItemPedidoService;

import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.List;

@Service
public class ItemPedidoServiceImpl extends ServiceImpl<ItemPedidoDtoSave, ItemPedidoDtoSend, ItemPedido> implements ItemPedidoService {
    private final ItemPedidoRepository itemPedidoRepository;
    private final ItemPedidoMapper itemPedidoMapper;
    private  final PedidoRepository pedidoRepository;
    private  final ProductoRepository productoRepository;
    protected ItemPedidoServiceImpl(ItemPedidoRepository itemPedidoRepository,ItemPedidoMapper itemPedidoMapper, PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        super(itemPedidoRepository,itemPedidoMapper);
        this.itemPedidoRepository = itemPedidoRepository;
        this.itemPedidoMapper = itemPedidoMapper;
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ItemPedidoDto> ObtenerItemPedidosPorIdPedido(Long idPedido) {
        return List.of();
    }

    @Override
    public List<ItemPedidoDto> ObtenerItemPedidosPorProductoEspecifico(Long idProducto) {
        return List.of();
    }

    @Override
    public List<ItemPedidoDto> ObtenerLaSumaTotalDeVentasDeProducto(Long idProducto) {
        return List.of();
    }

    @Override
    public String value() {
        return "";
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}

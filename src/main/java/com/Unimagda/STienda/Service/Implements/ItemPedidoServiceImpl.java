package com.Unimagda.STienda.Service.Implements;


import com.Unimagda.STienda.DTO.Save.ItemPedidoDtoSave;
import com.Unimagda.STienda.DTO.Send.ItemPedidoDtoSend;
import com.Unimagda.STienda.Entity.ItemPedido;
import com.Unimagda.STienda.Entity.Pedido;
import com.Unimagda.STienda.Entity.Producto;
import com.Unimagda.STienda.Mapper.Mappers.ItemPedidoMapper;
import com.Unimagda.STienda.Repository.Repositorys.ItemPedidoRepository;
import com.Unimagda.STienda.Repository.Repositorys.PedidoRepository;
import com.Unimagda.STienda.Repository.Repositorys.ProductoRepository;
import com.Unimagda.STienda.Service.ServiceImpl;
import com.Unimagda.STienda.Service.Services.ItemPedidoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoServiceImpl extends ServiceImpl<ItemPedidoDtoSave, ItemPedidoDtoSend, ItemPedido> implements ItemPedidoService {
    private final ItemPedidoRepository itemPedidoRepository;
    private final ItemPedidoMapper itemPedidoMapper;
    private  final PedidoRepository pedidoRepository;
    private  final ProductoRepository productoRepository;

    protected ItemPedidoServiceImpl(ItemPedidoRepository itemPedidoRepository,
                                    ItemPedidoMapper itemPedidoMapper,
                                    PedidoRepository pedidoRepository,
                                    ProductoRepository productoRepository) {
        super(itemPedidoRepository, itemPedidoMapper);
        this.itemPedidoRepository = itemPedidoRepository;
        this.itemPedidoMapper = itemPedidoMapper;
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ItemPedidoDtoSend> ObtenerListaItemPedidoPorIdPedido(Long idPedido) {
        List<ItemPedido> itemPedidoList = itemPedidoRepository.findByIdPedido(idPedido);
        return itemPedidoMapper.ListEntityToDtoSend(itemPedidoList);
    }

    @Override
    public List<ItemPedidoDtoSend> ObtenerItemPedidosPorProductoEspecifico(Long idProducto) {
        List<ItemPedido> itemPedidoList = itemPedidoRepository.findByIdProducto(idProducto);
        return itemPedidoMapper.ListEntityToDtoSend(itemPedidoList);
    }

    @Override
    public Double ObtenerLaSumaTotalDeVentasDeProducto(Long idProducto) {
        return itemPedidoRepository.SumaTotalDeVentaPorProducto(idProducto);
    }

    @Override
    public ItemPedidoDtoSend save(ItemPedidoDtoSave itemPedidoDtoSave, Long idProducto, Long idPedido) {
        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
        Optional<Producto> producto = productoRepository.findById(idProducto);
        ItemPedido itemPedido = itemPedidoMapper.dtoSaveToEntity(itemPedidoDtoSave);
        if(pedido.isEmpty()||producto.isEmpty())
            throw new RuntimeException("Pedido no encontrado");
        itemPedido.setPedido(pedido.get());
        itemPedido.setProducto(producto.get());
        producto.get().getItemPedidos().add(itemPedido);
        pedido.get().getItemPedidos().add(itemPedido);
        return itemPedidoMapper.EntityToDtoSend(itemPedidoRepository.save(itemPedido));
    }

    @Override
    public ItemPedidoDtoSend update(ItemPedidoDtoSave itemPedidoDtoSave, Long id) {
        Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(id);
        if (itemPedido.isEmpty())
            throw new RuntimeException("item pedido No encontrado");
        ItemPedido itemPedidoUpdate = itemPedido.get().itemPedidoUpdate(itemPedidoMapper.dtoSaveToEntity(itemPedidoDtoSave));
        return itemPedidoMapper.EntityToDtoSend(itemPedidoRepository.save(itemPedidoUpdate));
        }

}

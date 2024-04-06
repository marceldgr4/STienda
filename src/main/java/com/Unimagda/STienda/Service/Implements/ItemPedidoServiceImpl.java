package com.Unimagda.STienda.Service.Implements;

import com.Unimagda.STienda.DTO.ItemPedidoDto;
import com.Unimagda.STienda.Mapper.ItemPedidoMapper;
import com.Unimagda.STienda.Repository.ItemPedidoRepository;
import com.Unimagda.STienda.Service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {
    private final ItemPedidoRepository itemPedidoRepository;
    private final ItemPedidoMapper itemPedidoMapper;

    @Autowired

    public ItemPedidoServiceImpl(ItemPedidoRepository itemPedidoRepository, ItemPedidoMapper itemPedidoMapper) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.itemPedidoMapper = itemPedidoMapper;
    }
    @Override
    public List<ItemPedidoDto> ObtenerItemPedidosPorIdPedido(Long idPedido) {
        return itemPedidoRepository.findByPedidoId(idPedido)
                .stream()
                .map(itemPedidoMapper::itemPedidoToItemPedidoDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<ItemPedidoDto>ObtenerItemPedidosPorProductoEspecifico(Long idProducto) {
        return itemPedidoRepository.findByProductoId(idProducto)
                .stream()
                .map(itemPedidoMapper::itemPedidoToItemPedidoDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<ItemPedidoDto> ObtenerLaSumaTotalDeVentasDeProducto(Long idProducto) {
        return itemPedidoRepository.CalcularTotalDeVentaPorProducto(idProducto);

    }
}

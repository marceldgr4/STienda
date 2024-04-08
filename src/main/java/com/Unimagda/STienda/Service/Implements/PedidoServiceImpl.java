package com.Unimagda.STienda.Service.Implements;

import com.Unimagda.STienda.DTO.Dto.PedidoDto;
import com.Unimagda.STienda.Entity.Cliente;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import com.Unimagda.STienda.Mapper.Mappers.PedidoMapper;
import com.Unimagda.STienda.Repository.Repositorys.PedidoRepository;
import com.Unimagda.STienda.Service.Services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {
    public final PedidoRepository pedidoRepository;
    public  final PedidoMapper pedidoMapper;

    @Autowired

    public PedidoServiceImpl(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoMapper = pedidoMapper;
    }
    @Override
    public List<PedidoDto> ObtenerPedidoEntreDosFechas(LocalDateTime fechaIncio, LocalDateTime fechaFin){
        return pedidoRepository.findByFechaPedidoBetween(fechaIncio,fechaFin )
                .stream()
                .map(pedidoMapper::pedidoToPedidoDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<PedidoDto> ObtenerClienteYEstado(Cliente Cliente, EstadoDePedido estadoDePedido){
        return pedidoRepository.findByClienteAndStatus(Cliente,estadoDePedido)
                .stream()
                .map(pedidoMapper::pedidoToPedidoDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<PedidoDto> ObtenerPedidoConProductos(Long idCliente){
        return pedidoRepository.findByClienteId(idCliente)
                .stream()
                .map(pedidoMapper::pedidoToPedidoDto)
                .collect(Collectors.toList());

    }
}

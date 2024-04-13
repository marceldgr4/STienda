package com.Unimagda.STienda.Service.Implements;


import com.Unimagda.STienda.DTO.Save.PedidoDtoSave;
import com.Unimagda.STienda.DTO.Send.PedidoDtoSend;

import com.Unimagda.STienda.Entity.Cliente;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import com.Unimagda.STienda.Entity.Pedido;
import com.Unimagda.STienda.Mapper.Mappers.PedidoMapper;
import com.Unimagda.STienda.Repository.Repositorys.ClienteRepository;

import com.Unimagda.STienda.Repository.Repositorys.PedidoRepository;
import com.Unimagda.STienda.Service.ServiceImpl;
import com.Unimagda.STienda.Service.Services.PedidoService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class PedidoServiceImpl extends ServiceImpl<PedidoDtoSave, PedidoDtoSend, Pedido> implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final ClienteRepository clienteRepository;
    protected PedidoServiceImpl(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper, ClienteRepository clienteRepository) {
    super(pedidoRepository, pedidoMapper);
       this.pedidoRepository = pedidoRepository;
       this.pedidoMapper = pedidoMapper;
       this.clienteRepository = clienteRepository;

    }


    @Override
    public Page<PedidoDtoSend> ObtenerPedidoEntreDosFechas(LocalDateTime fichaInicio, LocalDateTime fichaFin) {
        Pageable pageable = PageRequest.of(0,10);
        Page<Pedido> listaPedido =pedidoRepository.findByFechaPedidoBetween(pageable,fichaInicio,fichaFin);
        return listaPedido.map(pedidoMapper::EntityToDtoSend);
    }

    @Override
    public Page<PedidoDtoSend> ObtenerClienteYEstado(Long idCliente, EstadoDePedido estadoDePedido) {
        Pageable pageable = PageRequest.of(0,10);
        Page<Pedido> listaPedido = pedidoRepository.findByClienteAndStatus(pageable, idCliente , estadoDePedido);
        return listaPedido.map(pedidoMapper::EntityToDtoSend);
    }

    @Override
    public Page<PedidoDtoSend> ObtenerClientePorItem(Long idCliente) {
        Pageable pageable = PageRequest.of(0,10);
        Page<Pedido> listaPedido = pedidoRepository.findByClienteWithItemsPedido(pageable, idCliente);
        return listaPedido.map(pedidoMapper::EntityToDtoSend);
    }

    @Override
    public PedidoDtoSend Update(PedidoDtoSave pedidoDtoSave, Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if(pedido.isEmpty()){
            return pedidoMapper.EntityToDtoSend( pedidoRepository.save(pedidoMapper.dtoSaveToEntity(pedidoDtoSave)));
        }
        Pedido PedidoUpdate = pedido.get().UpdatePedido(pedidoMapper.dtoSaveToEntity(pedidoDtoSave));
        return pedidoMapper.EntityToDtoSend( PedidoUpdate);
    }

    @Override
    public PedidoDtoSend save(PedidoDtoSave pedidoDtoSave, Long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(()-> new RuntimeException("Cliente no encontrado"));
        Pedido pedido = pedidoMapper.dtoSaveToEntity(pedidoDtoSave);
        pedido.setCliente(cliente);
        pedido.setFechaPedido(LocalDateTime.now());
        cliente.getPedidos().add(pedido);
        return pedidoMapper.EntityToDtoSend(pedidoRepository.save(pedido));


    }
}

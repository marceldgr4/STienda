package com.Unimagda.STienda.Service.Implements;


import com.Unimagda.STienda.DTO.Save.PedidoDtoSave;
import com.Unimagda.STienda.DTO.Send.PedidoDtoSend;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import com.Unimagda.STienda.Entity.Pedido;
import com.Unimagda.STienda.Mapper.Mappers.PedidoMapper;
import com.Unimagda.STienda.Repository.Repositorys.ClienteRepository;
import com.Unimagda.STienda.Repository.Repositorys.PedidoRepository;
import com.Unimagda.STienda.Service.ServiceImpl;
import com.Unimagda.STienda.Service.Services.PedidoService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class PedidoServiceImpl extends ServiceImpl<PedidoDtoSave, PedidoDtoSend, Pedido>implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final ClienteRepository clienteRepository;
    protected PedidoServiceImpl(PedidoRepository PedidoRepository, PedidoMapper PedidoMapper, ClienteRepository ClienteRepository) {
        super(pedidoRepository, pedidoMapper);
        this.pedidoRepository = PedidoRepository;
        this.pedidoMapper = PedidoMapper;
        this.clienteRepository = ClienteRepository;
    }


    @Override
    public Page<PedidoDtoSend> ObtenerPedidoEntreDosFechas(LocalDateTime fichaInicio, LocalDateTime fichaFin) {
        return null;
    }

    @Override
    public Page<PedidoDtoSend> ObtenerClienteYEstado(Long idCliente, EstadoDePedido estadoDePedido) {
        return null;
    }

    @Override
    public Page<PedidoDtoSend> ObtenerClientePorItem(Long idCliente) {
        return null;
    }

    @Override
    public PedidoDtoSend Update(PedidoDtoSave pedidoDtoSave, Long id) {
        return null;
    }

    @Override
    public PedidoDtoSend save(PedidoDtoSave pedidoDtoSave, Long idCliente) {
        return null;
    }
}

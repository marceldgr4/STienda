package com.Unimagda.STienda.Service.Implements;


import com.Unimagda.STienda.DTO.Save.PagoDtoSave;
import com.Unimagda.STienda.DTO.Send.PagoDtoSend;
import com.Unimagda.STienda.Entity.Enum.MetodoDePago;
import com.Unimagda.STienda.Entity.Pago;

import com.Unimagda.STienda.Entity.Pedido;
import com.Unimagda.STienda.Mapper.Mappers.PagoMapper;
import com.Unimagda.STienda.Repository.Repositorys.PagoRepository;
import com.Unimagda.STienda.Repository.Repositorys.PedidoRepository;
import com.Unimagda.STienda.Service.ServiceImpl;
import com.Unimagda.STienda.Service.Services.PagoService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PagoServiceImpl extends ServiceImpl<PagoDtoSave, PagoDtoSend,Pago> implements PagoService {
    private final PagoRepository pagoRepository;
    private  final PedidoRepository pedidoRepository;
    private final PagoMapper pagoMapper;
    protected PagoServiceImpl(PagoRepository pagoRepository, PedidoRepository pedidoRepository, PagoMapper pagoMapper) {
        super(pagoRepository, pagoMapper);
        this.pagoRepository = pagoRepository;
        this.pedidoRepository = pedidoRepository;
        this.pagoMapper = pagoMapper;

    }

    @Override
    public Page<PagoDtoSend> BuscarPorRangoDeFecha(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        Pageable pageable = PageRequest.of(0,10);
        Page<Pago> ListaDePago = pagoRepository.findByFechaDePagoBetween(pageable, fechaInicial, fechaFinal);
        return ListaDePago.map(pagoMapper::EntityToDtoSend);
    }

    @Override
    public Page<PagoDtoSend> buscarMetodoDePagoYPedido_IDPedido(MetodoDePago metodoDePago, Long idPedido) {
        Pageable pageable = PageRequest.of(0,10);
        Page<Pago> ListaDePago =pagoRepository.findByPedidoIdAndMetodoDePago(pageable, idPedido, metodoDePago);
        return ListaDePago.map(pagoMapper::EntityToDtoSend);
    }

    @Override
    public PagoDtoSend save(PagoDtoSave pagoDtoSave, Long idPedido) {
        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
        Pago pago = pagoMapper.dtoSaveToEntity(pagoDtoSave);
        pago.setPedido(pedido.get());
        pedido.get().setPago(pago);
        return pagoMapper.EntityToDtoSend(pagoRepository.save(pago));
    }

    @Override
    public PagoDtoSend Update(PagoDtoSave pagoDtoSave, Long id) {
        Optional<Pago> pago = pagoRepository.findById(id);
        if (pago.isEmpty()) {
            return pagoMapper.EntityToDtoSend(pagoRepository.save(pagoMapper.dtoSaveToEntity(pagoDtoSave)));
        }
        Pago pagoUpdate =pago.get().pagoUpdate(pagoMapper.dtoSaveToEntity(pagoDtoSave));
        return pagoMapper.EntityToDtoSend(pagoRepository.save(pagoUpdate));
    }



}

package com.Unimagda.STienda.Service.Implements;


import com.Unimagda.STienda.DTO.Save.DetalleEnvioDtoSave;

import com.Unimagda.STienda.DTO.Send.DetalleEnvioDtoSend;

import com.Unimagda.STienda.Entity.DetalleEnvio;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import com.Unimagda.STienda.Entity.Pedido;
import com.Unimagda.STienda.Mapper.Mappers.DetalleEnvioMapper;
import com.Unimagda.STienda.Repository.Repositorys.DetalleEnvioRepository;
import com.Unimagda.STienda.Repository.Repositorys.PedidoRepository;
import com.Unimagda.STienda.Service.ServiceImpl;
import com.Unimagda.STienda.Service.Services.DetalleEnvioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DetalleEnvioServiceImpl extends ServiceImpl<DetalleEnvioDtoSave, DetalleEnvioDtoSend, DetalleEnvio> implements DetalleEnvioService {

    private final DetalleEnvioRepository detalleEnvioRepository;
    private final DetalleEnvioMapper detalleEnvioMapper;
    private  final PedidoRepository pedidoRepository;

    protected DetalleEnvioServiceImpl(DetalleEnvioRepository detalleEnvioRepository,DetalleEnvioMapper detalleEnvioMapper,PedidoRepository pedidoRepository){
        super(detalleEnvioRepository,detalleEnvioMapper);
        this.detalleEnvioRepository = detalleEnvioRepository;
        this.detalleEnvioMapper = detalleEnvioMapper;
        this.pedidoRepository = pedidoRepository;
    }
    @Override
    public List<DetalleEnvioDtoSend> ObtenerIdPedido(Long idPedido){
        List<DetalleEnvio> detalleEnvioList = detalleEnvioRepository.findByPedido_IdPedido(idPedido);
        return  detalleEnvioMapper.ListEntityToDtoSend(detalleEnvioList);
    }
    @Override
    public List<DetalleEnvioDtoSend>ObtenerDetalleDeEnvioPorTransportadora(String transportadora){
        List<DetalleEnvio> detalleEnvioList = detalleEnvioRepository.findByTransportadora( transportadora);
        return detalleEnvioMapper.ListEntityToDtoSend(detalleEnvioList);

    }
    @Override
    public List<DetalleEnvioDtoSend> ObtenerDetalleDeEnvioPorEstado(EstadoDePedido estadoDePedido){
        List<DetalleEnvio>detalleEnvioList = detalleEnvioRepository.findByEstadoDePedido(estadoDePedido);
        return detalleEnvioMapper.ListEntityToDtoSend(detalleEnvioList);
    }

    @Override
    public DetalleEnvioDtoSend save(DetalleEnvioDtoSave detalleEnvioDtoSend, Long idPedido) {
        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
        if(pedido.isEmpty()) throw  new RuntimeException("Pedido no encontrado");
        DetalleEnvio detalleEnvio =detalleEnvioMapper.dtoSaveToEntity(detalleEnvioDtoSend);
        pedido.get().setDetalleEnvio(detalleEnvio);
        return detalleEnvioMapper.EntityToDtoSend(detalleEnvioRepository.save(detalleEnvio));
    }

    @Override
    public DetalleEnvioDtoSend update(DetalleEnvioDtoSave detalleEnvioDtoSave, Long id) {
        Optional<DetalleEnvio> detalleEnvio = detalleEnvioRepository.findById(id);
        if(detalleEnvio.isEmpty()) throw new RuntimeException("Detalle envio no encontrado");
        DetalleEnvio detalleEnvio1 = detalleEnvio.get().detalleEnvioUpdate(
                detalleEnvioMapper.dtoSaveToEntity(detalleEnvioDtoSave));
        return detalleEnvioMapper.EntityToDtoSend(detalleEnvioRepository.save(detalleEnvio1));
    }





}

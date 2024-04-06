package com.Unimagda.STienda.Service.Implements;

import com.Unimagda.STienda.DTO.DetalleEnvioDto;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import com.Unimagda.STienda.Mapper.DetalleEnvioMapper;
import com.Unimagda.STienda.Repository.DetalleEnvioRepository;
import com.Unimagda.STienda.Service.DetalleEnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleEnvioServiceImpl implements DetalleEnvioService {

    private final DetalleEnvioRepository detalleEnvioRepository;
    private final DetalleEnvioMapper detalleEnvioMapper;

    @Autowired
    public DetalleEnvioServiceImpl(DetalleEnvioRepository detalleEnvioRepository,DetalleEnvioMapper detalleEnvioMapper){
        this.detalleEnvioRepository = detalleEnvioRepository;
        this.detalleEnvioMapper = detalleEnvioMapper;
    }
    @Override
    public List<DetalleEnvioDto> ObtenerDetalleEnvioPorId(Long idPedido){
        return   detalleEnvioRepository.findByPedidoId(idPedido)
                .stream()
                .map(detalleEnvioMapper::detalleEnvioToDetalleEnvioDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<DetalleEnvioDto>ObtenerDetalleDeEnvioPorTransportadora(String transportadora){
        return detalleEnvioRepository.findByTransportadora(transportadora)
                .stream()
                .map(detalleEnvioMapper::detalleEnvioToDetalleEnvioDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<DetalleEnvioDto> ObtenerDetalleDeEnvioPorEstado(EstadoDePedido estadoDePedido){
        return detalleEnvioRepository.findByEstadoDePedido(estadoDePedido)
                .stream()
                .map(detalleEnvioMapper::detalleEnvioToDetalleEnvioDto)
                .collect(Collectors.toList());
    }

//-----------------------------------------------------------------------

    @Override
    public List<DetalleEnvioDto>ObtenerNumeroDeGuiaYEstadoDelPedido(String NumeroDeGuia, EstadoDePedido estadoDePedido){
        return detalleEnvioRepository.findByNumeroDeGuiaAndAndEstadoDePedido(NumeroDeGuia,estadoDePedido)
                .stream()
                .map(detalleEnvioMapper::detalleEnvioToDetalleEnvioDto)
                .collect(Collectors.toList());
    }
}

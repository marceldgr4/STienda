package com.Unimagda.STienda.Service;

import com.Unimagda.STienda.DTO.DetalleEnvioDto;
import com.Unimagda.STienda.Entity.DetalleEnvio;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import com.Unimagda.STienda.Entity.Enum.MetodoDePago;
import com.Unimagda.STienda.Mapper.DetalleEnvioMapper;
import com.Unimagda.STienda.Repository.DetalleEnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleEnvioService {
    private final DetalleEnvioRepository detalleEnvioRepository;
    private final DetalleEnvioMapper detalleEnvioMapper;
    @Autowired
    public DetalleEnvioService(DetalleEnvioRepository detalleEnvioRepository,DetalleEnvioMapper detalleEnvioMapper){
        this.detalleEnvioRepository = detalleEnvioRepository;
        this.detalleEnvioMapper = detalleEnvioMapper;
    }
    public List<DetalleEnvioDto>ObtenerDetalleEnvioPorId(Long idPedido){
       return   detalleEnvioRepository.findByPedidoId(idPedido)
               .stream()
               .map(detalleEnvioMapper::detalleEnvioToDetalleEnvioDto)
               .collect(Collectors.toList());
    }
    public List<DetalleEnvioDto>ObtenerDetalleDeEnvioPorTransportadora(String transportadora){
        return detalleEnvioRepository.findByTransportadora(transportadora)
                .stream()
                .map(detalleEnvioMapper::detalleEnvioToDetalleEnvioDto)
                .collect(Collectors.toList());
    }
    public List<DetalleEnvioDto> ObtenerDetalleDeEnvioPorEstado(EstadoDePedido estadoDePedido){
        return detalleEnvioRepository.findByEstadoDePedido(estadoDePedido)
                .stream()
                .map(detalleEnvioMapper::detalleEnvioToDetalleEnvioDto)
                .collect(Collectors.toList());
    }

//-----------------------------------------------------------------------


    public List<DetalleEnvioDto>ObtenerNumeroDeGuiaYEstadoDelPedido(String NumeroDeGuia, EstadoDePedido estadoDePedido){
        return detalleEnvioRepository.findByNumeroDeGuiaAndAndEstadoDePedido(NumeroDeGuia,estadoDePedido)
                .stream()
                .map(detalleEnvioMapper::detalleEnvioToDetalleEnvioDto)
                .collect(Collectors.toList());
    }
}

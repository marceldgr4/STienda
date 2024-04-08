package com.Unimagda.STienda.Service.Implements;

import com.Unimagda.STienda.DTO.Dto.PagoDto;
import com.Unimagda.STienda.Entity.Enum.MetodoDePago;
import com.Unimagda.STienda.Entity.Pago;
import com.Unimagda.STienda.Mapper.ClienteMapper;
import com.Unimagda.STienda.Mapper.PagoMapper;
import com.Unimagda.STienda.Repository.PagoRepository;
import com.Unimagda.STienda.Service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PagoServiceImpl implements PagoService {
    private final PagoRepository pagoRepository;
    private final ClienteMapper clienteMapper;
    private final PagoMapper pagoMapper;

    @Autowired
    public PagoServiceImpl(PagoRepository pagoRepository, ClienteMapper clienteMapper, PagoMapper pagoMapper) {
        this.pagoRepository = pagoRepository;
        this.clienteMapper = clienteMapper;
        this.pagoMapper = pagoMapper;
    }
    public List<PagoDto> ObtenerPagoEnRangoDeFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return pagoRepository.findByFechaDePagoBetween(fechaInicio, fechaFin)
                .stream()
                .map(pagoMapper::PagoToPagoDto)
                .collect(Collectors.toList());
    }
    public List<PagoDto> ObtenerPagoPorIdPedidoYMetodoDePago(Long idPedido, MetodoDePago metodoDePago) {
        return pagoRepository.findByPedidoIdAndMetodoDePago(idPedido,metodoDePago)
                .stream()
                .map(pagoMapper::PagoToPagoDto)
                .collect(Collectors.toList());

    }
    //---------------------------------------------------------------------------
    //----Obterner otros datos-----------
    public List<PagoDto> ObtenerPagoPorIdCliente(Long idCliente) {
        return pagoRepository.findById(idCliente)
                .stream().map(pagoMapper::PagoToPagoDto)
                .collect(Collectors.toList());
    }

    public List<PagoDto>ObtenerTodo(){
        return pagoRepository.findAll().stream().map(pagoMapper::PagoToPagoDto).collect(Collectors.toList());
    }

    public List<PagoDto> ObtenerIdPago(Long idPago) {
        return pagoRepository.findById(idPago)
                .stream().map(pagoMapper::PagoToPagoDto)
                .collect(Collectors.toList());
    }
    //------------------------CRUD-----------------------
    public PagoDto CrearPago(PagoDto pagoDto) {
        Pago pago = pagoMapper.PagoDtoToPago(pagoDto);
        pago = pagoRepository.save(pago);
        return pagoMapper.PagoToPagoDto(pago);
    }
    public Pago ActualizarPago(Long idPago, Pago pago) {
        if (pagoRepository.existsById(pago.getId())){
            return pagoRepository.save(pago);
        }else{
            return null;
        }

    }
    public Boolean EliminarPago(Long idPago) {
        if (pagoRepository.existsById(idPago)){
            pagoRepository.deleteById(idPago);
            return true;
        }else {
            return false;
        }
    }
}

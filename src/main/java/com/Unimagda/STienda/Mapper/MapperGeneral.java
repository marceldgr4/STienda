package com.Unimagda.STienda.Mapper;

import com.Unimagda.STienda.DTO.Send.ItemPedidoDtoSend;
import com.Unimagda.STienda.Entity.DetalleEnvio;

import java.util.List;

public interface MapperGeneral<S, M, E > {
    S EntityToDtoSave(E e);
    E dtoSaveToEntity(S s);
    M EntityToDtoSend(E e);
    E dtoSendToEntity(M m);
    List<M>ListEntityToDtoSend(List<E> e);
    List<E> ListDtoSendToEntity(List<M> m);
}

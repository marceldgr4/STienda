package com.Unimagda.STienda.Mapper;

import com.Unimagda.STienda.DTO.Send.ItemPedidoDtoSend;

import java.util.List;

public interface MapperGeneral<S, M, E > {
    S EntityToDtoSave(E e);
    E dtoSaveToEntity(S s);
    M EntityToDtoSend(E e);
    E dtoSendToEntity(M m);
    List<ItemPedidoDtoSend> ListEntityToDtoSend(List<E> e);
    List<E> ListDtoSendToEntity(List<M> m);
}

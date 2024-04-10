package com.Unimagda.STienda.Service;


import com.Unimagda.STienda.DTO.Send.ClienteDtoSend;
import com.Unimagda.STienda.DTO.Send.DetalleEnvioDtoSend;
import com.Unimagda.STienda.Mapper.MapperGeneral;
import org.aspectj.apache.bcel.util.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public abstract class ServiceImpl<S,M,E> implements Service<S,M,E> {
    private final Repository<E> repository;
    private final MapperGeneral<S,M,E> mapper;

    protected ServiceImpl(Repository<E> repository, MapperGeneral<S, M, E> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    public Page<M> findAll(){
    Pageable pageable = PageRequest.of(0, 10);
    Page<E> ListUser = repository.findAll(pageable);
    return ListUser.map(mapper::EntityToDtoSend);
}
public Optional<M>findById(Long id) {
    Optional<E> User = Optional.ofNullable(repositoy.findById(id).orElseThrow(() ->
            new RuntimeException("entidad not encontrada")));
            return User.map(mapper::EntityToDtoSend);
    }
    public M save(S s){
    E e =mapper.dtoSaveToEntity(s);
    return mapper.EntityToDtoSend((repository.save(e)));
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }



}
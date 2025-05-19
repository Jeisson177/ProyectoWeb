package com.example.demo.DTO;

import org.mapstruct.Mapper;

import com.example.demo.entity.Cliente;

@Mapper
public interface ClienteMapper {
    ClienteMapper INSTANCE= Mappers.getMapper(ClienteMapper.class);

    ClienteDTO convert(Cliente cliente);
}

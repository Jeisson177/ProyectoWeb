package com.example.demo.DTOs;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.entity.Operador;

@Mapper
public interface OperadorMapper {
    OperadorMapper INSTANCE = Mappers.getMapper(OperadorMapper.class);

    OperadorDTO convert(Operador operador);
    
}

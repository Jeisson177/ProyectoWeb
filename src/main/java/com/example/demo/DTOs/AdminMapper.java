package com.example.demo.DTOs;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.entity.Administrador;

@Mapper
public class AdminMapper {
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    AdminDTO convert(Administrador admin);
    
}

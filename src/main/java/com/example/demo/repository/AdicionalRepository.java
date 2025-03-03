package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Adicional;

@Repository
public interface AdicionalRepository extends JpaRepository<Adicional, Long> {

    
} 
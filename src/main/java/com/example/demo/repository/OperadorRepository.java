package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Operador;

@Repository
public interface OperadorRepository extends JpaRepository<Operador, Long> {
    Optional<Operador> findFirstByDisponibleTrueOrderById();}
package com.example.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Administrador;


@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    
}

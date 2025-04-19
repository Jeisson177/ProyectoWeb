package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Domiciliario;

@Repository
public interface DomiciliarioRepository extends JpaRepository<Domiciliario, Long> {

   
    @Query("SELECT d FROM Domiciliario d WHERE d.disponibilidad = true ORDER BY d.id LIMIT 1")
    Optional<Domiciliario> findFirstAvailable();
} 
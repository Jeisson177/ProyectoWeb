package com.example.demo.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Administrador;


@Repository
public class AdministradorRepository {

    private Map<String, Administrador> administradores = new HashMap<>();

    public AdministradorRepository() {
        Administrador admin = new Administrador(1, "Juan", "Perez", "JPerez1214", "juanperez855");
        administradores.put(admin.getUsuario(), admin);
        Administrador admin2 = new Administrador(2, "Maria", "Jaramillo", "Jaramillo_M", "Bgta5862");
        administradores.put(admin2.getUsuario(), admin2);
    }


    
}

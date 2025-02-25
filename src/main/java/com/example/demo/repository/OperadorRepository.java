package com.example.demo.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Operador;

@Repository
public class OperadorRepository {

    private Map<String, Operador> operadores = new HashMap<>();

    public OperadorRepository() {
        
        operadores.put("operador1", new Operador(1, "Carlos Gómez", "carlosg", "clave123"));
        operadores.put("operador2", new Operador(2, "María López", "marial", "clave456"));
        operadores.put("operador3", new Operador(3, "Pedro Ramírez", "pedror", "clave789"));
        operadores.put("operador4", new Operador(4, "Ana Martínez", "anam", "clave101"));
        operadores.put("operador5", new Operador(5, "Luisa Fernández", "luisaf", "clave112"));
        operadores.put("operador6", new Operador(6, "Jorge Díaz", "jorged", "clave131"));
    }

    public Operador getOperador(String idOperador) {
        return operadores.get(idOperador);
    }

    public Map<String, Operador> getAllOperadores() {
        return operadores;
    }

    // Método para agregar un nuevo operador
    public void addOperador(Operador operador) {
        String newId = "operador" + (operadores.size() + 1);
        operador.setIdOperador(operadores.size() + 1);
        operadores.put(newId, operador);
    }

    // Método para actualizar un operador existente
    public void updateOperador(String idOperador, Operador operador) {
        if (operadores.containsKey(idOperador)) {
            operadores.put(idOperador, operador);
        }
    }

    // Método para eliminar un operador por su ID
    public void deleteOperador(String idOperador) {
        operadores.remove(idOperador);
    }

    // Método para autenticar un operador (login)
    public Operador autenticarOperador(String usuario, String contrasenia) {
        for (Operador operador : operadores.values()) {
            if (operador.getUsuario().equals(usuario) && operador.getContrasena().equals(contrasenia)) {
                return operador;
            }
        }
        return null; // Si no encuentra coincidencias
    }
}
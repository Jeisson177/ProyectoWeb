package com.example.demo.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Domiciliario;

@Repository
public class DomiciliarioRepository {

    private static Map<Integer, Domiciliario> domiciliarios = new HashMap<>();

    public DomiciliarioRepository() {
        
        domiciliarios.put(1, new Domiciliario(1, "Mario Rossi", 3112345, true));
        domiciliarios.put(2, new Domiciliario(2, "Luigi Verdi", 2345679, true));
        domiciliarios.put(3, new Domiciliario(3, "Giovanni Bianchi", 3135680, false));
        domiciliarios.put(4, new Domiciliario(4, "Francesco Romano", 3113581, true));
        domiciliarios.put(5, new Domiciliario(5, "Antonio Esposito", 31123482, true));
        domiciliarios.put(6, new Domiciliario(6, "Paolo Ferrari", 3112345, false));
        domiciliarios.put(7, new Domiciliario(7, "Marco Ricci", 3112384, true));
        domiciliarios.put(8, new Domiciliario(8, "Stefano Moretti", 37892345, true));
        domiciliarios.put(9, new Domiciliario(9, "Luca Conti", 31123496, true));
        domiciliarios.put(10, new Domiciliario(10, "Roberto Marini", 39915687, false));
    }

    public Domiciliario getDomiciliario(int id) {
        return domiciliarios.get(id);
    }

    public Collection<Domiciliario> getAllDomiciliarios() {
        return domiciliarios.values();
    }

    // Método para agregar un nuevo domiciliario
    public void addDomiciliario(Domiciliario domiciliario) {
        int newId = domiciliarios.size() + 1;
        domiciliario.setId(newId);
        domiciliarios.put(newId, domiciliario);
    }

    // Método para actualizar un domiciliario existente
    public void updateDomiciliario(int id, Domiciliario domiciliario) {
        if (domiciliarios.containsKey(id)) {
            domiciliario.setId(id);
            domiciliarios.put(id, domiciliario);
        }
    }

    // Método para eliminar un domiciliario por su ID
    public void deleteDomiciliario(int id) {
        domiciliarios.remove(id);
    }

    // Método para obtener domiciliarios disponibles
    public Map<Integer, Domiciliario> getDomiciliariosDisponibles() {
        Map<Integer, Domiciliario> disponibles = new HashMap<>();
        for (Map.Entry<Integer, Domiciliario> entry : domiciliarios.entrySet()) {
            if (entry.getValue().isDisponibilidad()) {
                disponibles.put(entry.getKey(), entry.getValue());
            }
        }
        return disponibles;
    }
}
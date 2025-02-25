package com.example.demo.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Adicional;

@Repository
public class AdicionalRepository {

    private Map<Integer, Adicional> adicionales = new HashMap<>();

    public AdicionalRepository() {
        
        adicionales.put(1, new Adicional(1, 1, "Queso Parmesano Extra", 500));
        adicionales.put(2, new Adicional(2, 2, "Salsa de Tomate Extra", 300));
        adicionales.put(3, new Adicional(3, 3, "Aceitunas Negras", 400));
        adicionales.put(4, new Adicional(4, 4, "Champiñones Salteados", 600));
        adicionales.put(5, new Adicional(5, 5, "Albahaca Fresca", 200));
        adicionales.put(6, new Adicional(6, 6, "Pepperoni Extra", 700));
        adicionales.put(7, new Adicional(7, 7, "Jalapeños", 450));
        adicionales.put(8, new Adicional(8, 8, "Queso Mozzarella Extra", 550));
        adicionales.put(9, new Adicional(9, 9, "Aceite de Oliva Virgen Extra", 250));
        adicionales.put(10, new Adicional(10, 10, "Pimientos Asados", 350));
        adicionales.put(11, new Adicional(11, 11, "Anchoas", 800));
        adicionales.put(12, new Adicional(12, 12, "Alcaparras", 300));
        adicionales.put(13, new Adicional(13, 13, "Provolone Fundido", 650));
        adicionales.put(14, new Adicional(14, 14, "Rúcula Fresca", 400));
        adicionales.put(15, new Adicional(15, 15, "Tomates Secos", 500));
        adicionales.put(16, new Adicional(16, 16, "Jamón Serrano", 900));
        adicionales.put(17, new Adicional(17, 17, "Queso Gorgonzola", 750));
        adicionales.put(18, new Adicional(18, 18, "Salsa Pesto", 600));
        adicionales.put(19, new Adicional(19, 19, "Ajo Asado", 300));
        adicionales.put(20, new Adicional(20, 20, "Trufa Negra", 1500));
    }

    public Adicional getAdicional(int adicional_id) {
        return adicionales.get(adicional_id);
    }

    public Collection<Adicional> getAllAdicionales() {
        return adicionales.values();
    }

    // Método para agregar un nuevo adicional
    public void addAdicional(Adicional adicional) {
        int newId = adicionales.size() + 1;
        adicional.setAdicional_id(newId);
        adicionales.put(newId, adicional);
    }

    // Método para actualizar un adicional existente
    public void updateAdicional(int id, Adicional adicional) {
        if (adicionales.containsKey(id)) {
            adicional.setAdicional_id(id);
            adicionales.put(id, adicional);
        }
    }

    // Método para eliminar un adicional por su ID
    public void deleteAdicional(int id) {
        adicionales.remove(id);
    }
}
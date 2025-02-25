package com.example.demo.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Cliente;

@Repository
public class ClienteRepository {

    private Map<Integer, Cliente> clientes = new HashMap<>();

    public ClienteRepository() {
        
        clientes.put(1, new Cliente("1", "Juan", "Pérez", "jperez", "1234", "Calle 123", "555-1234"));
        clientes.put(2, new Cliente("2", "María", "Gómez", "mgomez", "5678", "Avenida 456", "555-5678"));
        clientes.put(3, new Cliente("3", "Carlos", "López", "clopez", "91011", "Boulevard 789", "555-9101"));
        clientes.put(4, new Cliente("4", "Ana", "Martínez", "amartinez", "121314", "Calle 321", "555-1213"));
        clientes.put(5, new Cliente("5", "Luis", "Rodríguez", "lrodriguez", "151617", "Avenida 654", "555-1516"));
        clientes.put(6, new Cliente("6", "Pedro", "Sánchez", "psanchez", "181920", "Calle 987", "555-1819"));
        clientes.put(7, new Cliente("7", "Laura", "Fernández", "lfernandez", "212223", "Avenida 654", "555-2122"));
        clientes.put(8, new Cliente("8", "Miguel", "Díaz", "mdiaz", "242526", "Boulevard 321", "555-2425"));
        clientes.put(9, new Cliente("9", "Sofía", "Ruiz", "sruiz", "272829", "Calle 159", "555-2728"));
        clientes.put(10, new Cliente("10", "Javier", "Hernández", "jhernandez", "303132", "Avenida 753", "555-3031"));
        clientes.put(11, new Cliente("11", "Elena", "Torres", "etorres", "333435", "Calle 852", "555-3334"));
        clientes.put(12, new Cliente("12", "Diego", "Vargas", "dvargas", "363738", "Boulevard 456", "555-3637"));
        clientes.put(13, new Cliente("13", "Carmen", "Jiménez", "cjimenez", "394041", "Avenida 963", "555-3940"));
        clientes.put(14, new Cliente("14", "Ricardo", "Molina", "rmolina", "424344", "Calle 741", "555-4243"));
        clientes.put(15, new Cliente("15", "Patricia", "Castro", "pcastro", "454647", "Boulevard 258", "555-4546"));
        clientes.put(16, new Cliente("16", "Fernando", "Ortega", "fortega", "484950", "Avenida 369", "555-4849"));
        clientes.put(17, new Cliente("17", "Lucía", "Navarro", "lnavarro", "515253", "Calle 753", "555-5152"));
        clientes.put(18, new Cliente("18", "Oscar", "Ramírez", "oramirez", "545556", "Boulevard 951", "555-5455"));
        clientes.put(19, new Cliente("19", "Isabel", "Reyes", "ireyes", "575859", "Avenida 357", "555-5758"));
        clientes.put(20, new Cliente("20", "Gabriel", "Morales", "gmorales", "606162", "Calle 456", "555-6061"));
        clientes.put(21, new Cliente("21", "Adriana", "Guerrero", "aguerrero", "636465", "Boulevard 789", "555-6364"));
        clientes.put(22, new Cliente("22", "Raúl", "Rojas", "rrojas", "666768", "Avenida 123", "555-6667"));
        clientes.put(23, new Cliente("23", "Mónica", "Silva", "msilva", "697071", "Calle 654", "555-6970"));
        clientes.put(24, new Cliente("24", "Héctor", "Cruz", "hcruz", "727374", "Boulevard 321", "555-7273"));
        clientes.put(25, new Cliente("25", "Natalia", "Peña", "npena", "757677", "Avenida 987", "555-7576"));
    }

    public Map<Integer, Cliente> getClientes() {
        return clientes;
    }

    // Método para obtener un cliente por su ID
    public Cliente findById(int id) {
        return clientes.get(id);
    }

    // Método para obtener todos los clientes
    public List<Cliente> findAll() {
        return clientes.values().stream().collect(Collectors.toList());
    }

    // Método para buscar clientes por nombre
    public List<Cliente> findByNombre(String nombre) {
        return clientes.values().stream()
                .filter(cliente -> cliente.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
    }

    // Método para buscar clientes por apellido
    public List<Cliente> findByApellido(String apellido) {
        return clientes.values().stream()
                .filter(cliente -> cliente.getApellido().equalsIgnoreCase(apellido))
                .collect(Collectors.toList());
    }

    // Método para buscar clientes por nombre de usuario
    public Cliente findByCorreoCliente(String correo) {
        return clientes.values().stream()
                .filter(cliente -> cliente.getCorreo().equalsIgnoreCase(correo))
                .findFirst()
                .orElse(null);
    }

    // Método para agregar un nuevo cliente
    public void addCliente(Cliente cliente) {
        int newId = clientes.size() + 1;
        cliente.setId(String.valueOf(newId));
        clientes.put(newId, cliente);
    }

    // Método para actualizar un cliente existente
    public void updateCliente(int id, Cliente cliente) {
        if (clientes.containsKey(id)) {
            cliente.setId(String.valueOf(id));
            clientes.put(id, cliente);
        }
    }

    // Método para eliminar un cliente por su ID
    public void deleteCliente(int id) {
        clientes.remove(id);
    }
}
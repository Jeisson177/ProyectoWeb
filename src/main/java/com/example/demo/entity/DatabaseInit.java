package com.example.demo.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import com.example.demo.repository.AdicionalRepository;
import com.example.demo.repository.AdministradorRepository;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.DomiciliarioRepository;
import com.example.demo.repository.OperadorRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProductoRepository;

import jakarta.transaction.Transactional;


@Controller
@Transactional
public class DatabaseInit implements ApplicationRunner{

    @Autowired
    ProductoRepository productoRepository;



    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    AdicionalRepository adicionalRepository;

    @Autowired
    AdministradorRepository administradorRepository;

    @Autowired
    DomiciliarioRepository domiciliarioRepository;

    @Autowired
    OperadorRepository operadorRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Producto producto1 = new Producto("Lasagna Boloñesa", 20000, "Lasagna Boloñesa con carne molida y salsa de tomate.", "Plato Principal");
        Producto producto2 = new Producto("Spaghetti Carbonara", 18000, "Spaghetti con auténtica salsa carbonara.", "Plato Principal");
        Producto producto3 = new Producto("Pizza Margherita", 15000, "Pizza clásica con mozzarella fresca y albahaca.", "Plato Principal");
        Producto producto4 = new Producto("Ravioli de Ricotta y Espinacas", 22000, "Ravioli rellenos de ricotta y espinacas.", "Plato Principal");
        Producto producto5 = new Producto("Penne al Pesto", 17000, "Penne con salsa pesto de albahaca y piñones.", "Plato Principal");
        Producto producto6 = new Producto("Tiramisu", 12000, "Postre italiano con mascarpone y cacao.", "Postre");
        Producto producto7 = new Producto("Risotto de Champiñones", 19000, "Risotto cremoso con champiñones frescos.", "Plato Principal");
        Producto producto8 = new Producto("Calzone", 16000, "Pizza rellena de jamón y mozzarella.", "Plato Principal");
        Producto producto9 = new Producto("Fettuccine Alfredo", 21000, "Fettuccine con salsa cremosa de parmesano.", "Plato Principal");
        Producto producto10 = new Producto("Cannoli Siciliani", 10000, "Postre siciliano de masa crujiente rellena de ricotta.", "Postre");
        Producto producto11 = new Producto("Pizza Pepperoni", 17000, "Pizza con una base crujiente, salsa de tomate, queso mozzarella fundido y rodajas de pepperoni dorado.", "Plato Principal");
        Producto producto12 = new Producto("Ossobuco", 25000, "Estofado de jarrete de ternera cocinado lentamente con vino blanco, verduras y hierbas aromáticas.", "Plato Principal");
        Producto producto13 = new Producto("Panna Cotta", 9000, "Postre italiano cremoso y suave, elaborado con nata, vainilla y azúcar.", "Postre");
        Producto producto14 = new Producto("Minestrone", 12000, "Sopa de verduras de temporada con pasta y frijoles, cocinada con hierbas aromáticas.", "Entrada");
        Producto producto15 = new Producto("Arancini", 11000, "Bolitas de arroz rellenas de queso y ragú, empanizadas y fritas hasta quedar doradas.", "Entrada");
        Producto producto16 = new Producto("Zuppa Toscana", 14000, "Sopa toscana con patatas, kale y chorizo en un caldo cremoso con un toque de ajo.", "Entrada");
        Producto producto17 = new Producto("Pizza Quattro Formaggi", 19000, "Pizza con una mezcla de cuatro quesos: mozzarella, gorgonzola, parmesano y fontina.", "Plato Principal");
        Producto producto18 = new Producto("Tortellini en Brodo", 16000, "Tortellini rellenos de carne en un caldo de pollo aromático.", "Plato Principal");
        Producto producto19 = new Producto("Carpaccio", 15000, "Láminas finas de carne cruda marinadas con aceite de oliva, limón y queso parmesano.", "Entrada");
        Producto producto20 = new Producto("Gelato", 7000, "Helado artesanal italiano con opciones de vainilla, chocolate o fresa.", "Postre");
        Producto producto21 = new Producto("Pizza Prosciutto", 18000, "Pizza con jamón crudo (prosciutto) y rúcula fresca sobre una base crujiente con queso mozzarella.", "Plato Principal");
        Producto producto22 = new Producto("Linguini Frutti di Mare", 23000, "Linguini con mariscos frescos en una salsa de tomate picante.", "Plato Principal");
        Producto producto23 = new Producto("Panettone", 14000, "Pan dulce italiano con frutas confitadas y pasas. Esponjoso y perfecto para acompañar con un café o té.", "Postre");
        Producto producto24 = new Producto("Espresso", 4000, "Café espresso italiano intenso y aromático, preparado con granos de café recién molidos.", "Bebida");
        Producto producto25 = new Producto("Limoncello", 9000, "Licor italiano de limón elaborado artesanalmente con cáscaras de limón y alcohol puro.", "Bebida");
        Producto producto26 = new Producto("Affogato", 8000, "Postre simple pero delicioso, hecho con una bola de helado de vainilla bañada con un shot caliente de espresso italiano.", "Postre");
        Producto producto27 = new Producto("Cappuccino", 5000, "Café cappuccino con una base de espresso, leche vaporizada y espuma de leche espesa.", "Bebida");
        Producto producto28 = new Producto("Tortellini con Salsa Alfredo", 19500, "Tortellini rellenos de queso en una cremosa salsa Alfredo con un toque de ajo.", "Plato Principal");
        Producto producto29 = new Producto("Focaccia con Romero", 9000, "Pan italiano horneado con aceite de oliva y romero fresco, perfecto para acompañar cualquier comida.", "Entrada");
        Producto producto30 = new Producto("Tartufo", 15000, "Helado italiano con un centro de chocolate derretido, cubierto con cacao en polvo.", "Postre");

// Guardar los nuevos productos en la base de datos

        productoRepository.saveAll(List.of(producto1, producto2, producto3, producto4, producto5, producto6, producto7, producto8, producto9, producto10,producto11,producto12,producto13,producto14,producto15,producto16,producto17,producto18,producto19,producto20,producto21,producto22,producto23,producto24,producto25,producto26,producto27,producto28,producto29,producto30));
        
    
        //Inicio los clientes
        clienteRepository.save(new Cliente("Juan", "Pérez", "jperez@example.com", "1234abc12", "Calle 123", "555-1234"));
        clienteRepository.save(new Cliente("María", "Gómez", "mgomez@example.com", "5678", "Avenida 456", "555-5678"));
        clienteRepository.save(new Cliente("Carlos", "López", "clopez@example.com", "91011", "Boulevard 789", "555-9101"));
        clienteRepository.save(new Cliente("Ana", "Martínez", "amartinez@example.com", "121314", "Calle 321", "555-1213"));
        clienteRepository.save(new Cliente("Luis", "Rodríguez", "lrodriguez@example.com", "151617", "Avenida 654", "555-1516"));
        clienteRepository.save(new Cliente("Pedro", "Sánchez", "psanchez@example.com", "181920", "Calle 987", "555-1819"));
        clienteRepository.save(new Cliente("Laura", "Fernández", "lfernandez@example.com", "212223", "Avenida 654", "555-2122"));
        clienteRepository.save(new Cliente("Miguel", "Díaz", "mdiaz@example.com", "242526", "Boulevard 321", "555-2425"));
        clienteRepository.save(new Cliente("Sofía", "Ruiz", "sruiz@example.com", "272829", "Calle 159", "555-2728"));
        clienteRepository.save(new Cliente("Javier", "Hernández", "jhernandez@example.com", "303132", "Avenida 753", "555-3031"));
        clienteRepository.save(new Cliente("Elena", "Torres", "etorres@example.com", "333435", "Calle 852", "555-3334"));
        clienteRepository.save(new Cliente("Diego", "Vargas", "dvargas@example.com", "363738", "Boulevard 456", "555-3637"));
        clienteRepository.save(new Cliente("Carmen", "Jiménez", "cjimenez@example.com", "394041", "Avenida 963", "555-3940"));
        clienteRepository.save(new Cliente("Ricardo", "Molina", "rmolina@example.com", "424344", "Calle 741", "555-4243"));
        clienteRepository.save(new Cliente("Patricia", "Castro", "pcastro@example.com", "454647", "Boulevard 258", "555-4546"));
        clienteRepository.save(new Cliente("Fernando", "Ortega", "fortega@example.com", "484950", "Avenida 369", "555-4849"));
        clienteRepository.save(new Cliente("Lucía", "Navarro", "lnavarro@example.com", "515253", "Calle 753", "555-5152"));
        clienteRepository.save(new Cliente("Oscar", "Ramírez", "oramirez@example.com", "545556", "Boulevard 951", "555-5455"));
        clienteRepository.save(new Cliente("Isabel", "Reyes", "ireyes@example.com", "575859", "Avenida 357", "555-5758"));
        clienteRepository.save(new Cliente("Gabriel", "Morales", "gmorales@example.com", "606162", "Calle 456", "555-6061"));
        clienteRepository.save(new Cliente("Adriana", "Guerrero", "aguerrero@example.com", "636465", "Boulevard 789", "555-6364"));
        clienteRepository.save(new Cliente("Raúl", "Rojas", "rrojas@example.com", "666768", "Avenida 123", "555-6667"));
        clienteRepository.save(new Cliente("Mónica", "Silva", "msilva@example.com", "697071", "Calle 654", "555-6970"));
        clienteRepository.save(new Cliente("Héctor", "Cruz", "hcruz@example.com", "727374", "Boulevard 321", "555-7273"));
        clienteRepository.save(new Cliente("Natalia", "Peña", "npena@example.com", "757677", "Avenida 987", "555-7576"));
        
    
        
        //Inicio los adicionales
        Adicional adicional1 = new Adicional(1, "Queso Parmesano Extra", 500, producto1);
        Adicional adicional2 = new Adicional(2, "Salsa de Tomate Extra", 300, producto1);
        Adicional adicional3 = new Adicional(1, "Aceitunas Negras", 400, producto2);
        Adicional adicional4 = new Adicional(2, "Champiñones Salteados", 600, producto2);
        Adicional adicional5 = new Adicional(1, "Albahaca Fresca", 200, producto3);
        Adicional adicional6 = new Adicional(3, "Pepperoni Extra", 700, producto3);
        Adicional adicional7 = new Adicional(2, "Jalapeños", 450, producto4);
        Adicional adicional8 = new Adicional(1, "Queso Mozzarella Extra", 550, producto4);
        Adicional adicional9 = new Adicional(2, "Aceite de Oliva Virgen Extra", 250, producto5);
        Adicional adicional10 = new Adicional(1, "Pimientos Asados", 350, producto5);
        Adicional adicional11 = new Adicional(2, "Parmesano Rallado", 500, producto11);
        Adicional adicional12 = new Adicional(1, "Salsa de Trufa", 1000, producto11);
        Adicional adicional13 = new Adicional(3, "Alcaparras", 400, producto12);
        Adicional adicional14 = new Adicional(1, "Panceta Crujiente", 700, producto12);
        Adicional adicional15 = new Adicional(2, "Queso Ricotta", 600, producto13);
        Adicional adicional16 = new Adicional(1, "Chocolate Fundido", 800, producto13);
        Adicional adicional17 = new Adicional(1, "Pan Tostado", 300, producto14);
        Adicional adicional18 = new Adicional(3, "Chorizo Español", 900, producto14);
        Adicional adicional19 = new Adicional(1, "Pesto Genovés", 650, producto15);
        Adicional adicional20 = new Adicional(2, "Pimientos Rellenos", 750, producto15);
        Adicional adicional21 = new Adicional(1, "Jamón Serrano Extra", 1000, producto21);
        Adicional adicional22 = new Adicional(2, "Mariscos Extra", 1500, producto22);
        Adicional adicional23 = new Adicional(1, "Frutas Confitadas Extra", 700, producto23);
        Adicional adicional24 = new Adicional(1, "Azúcar Morena", 300, producto24);
        Adicional adicional25 = new Adicional(1, "Hojas de Menta", 400, producto25);
        Adicional adicional26 = new Adicional(1, "Doble Shot de Espresso", 1000, producto26);
        Adicional adicional27 = new Adicional(2, "Canela en Polvo", 500, producto27);
        Adicional adicional28 = new Adicional(1, "Salsa de Trufa", 1200, producto28);
        Adicional adicional29 = new Adicional(1, "Aceite de Oliva Extra Virgen", 500, producto29);
        Adicional adicional30 = new Adicional(1, "Chocolate Rallado", 600, producto30);

        adicionalRepository.saveAll(List.of(adicional1, adicional2, adicional3, adicional4, adicional5, adicional6, adicional7, adicional8, adicional9, adicional10,adicional11,adicional12,adicional13,adicional14,adicional15,adicional16,adicional17,adicional18,adicional19,adicional20,adicional21,adicional22,adicional23,adicional24,adicional25,adicional26,adicional27,adicional28,adicional29,adicional30));

        // Asignar un adicional a cada producto (1:1 asignación)
producto1.getAdicionales().add(adicional1);
producto2.getAdicionales().add(adicional2);
producto3.getAdicionales().add(adicional3);
producto4.getAdicionales().add(adicional4);
producto5.getAdicionales().add(adicional5);
producto6.getAdicionales().add(adicional6);
producto7.getAdicionales().add(adicional7);
producto8.getAdicionales().add(adicional8);
producto9.getAdicionales().add(adicional9);
producto10.getAdicionales().add(adicional10);
producto11.getAdicionales().add(adicional11);
producto12.getAdicionales().add(adicional12);
producto13.getAdicionales().add(adicional13);
producto14.getAdicionales().add(adicional14);
producto15.getAdicionales().add(adicional15);
producto16.getAdicionales().add(adicional16);
producto17.getAdicionales().add(adicional17);
producto18.getAdicionales().add(adicional18);
producto19.getAdicionales().add(adicional19);
producto20.getAdicionales().add(adicional20);
producto21.getAdicionales().add(adicional21);
producto22.getAdicionales().add(adicional22);
producto23.getAdicionales().add(adicional23);
producto24.getAdicionales().add(adicional24);
producto25.getAdicionales().add(adicional25);
producto26.getAdicionales().add(adicional26);
producto27.getAdicionales().add(adicional27);
producto28.getAdicionales().add(adicional28);
producto29.getAdicionales().add(adicional29);
producto30.getAdicionales().add(adicional30);


        //Inicio de los Administradores 
        administradorRepository.save(new Administrador( "Juan", "Perez", "JPerez1214", "juanperez855"));
        administradorRepository.save(new Administrador( "Maria", "Jaramillo", "Jaramillo_M", "Bgta5862"));
    
        //Inicio de los domiciliarios

        domiciliarioRepository.save(new Domiciliario("Mario Rossi", 3112345, true));
        domiciliarioRepository.save(new Domiciliario("Luigi Verdi", 2345679, true));
        domiciliarioRepository.save(new Domiciliario("Giovanni Bianchi", 3135680, false));
        domiciliarioRepository.save(new Domiciliario("Francesco Romano", 3113581, true));
        domiciliarioRepository.save(new Domiciliario("Antonio Esposito", 31123482, true));
        domiciliarioRepository.save(new Domiciliario("Paolo Ferrari", 3112345, false));
        domiciliarioRepository.save(new Domiciliario("Marco Ricci", 3112384, true));
        domiciliarioRepository.save(new Domiciliario("Stefano Moretti", 37892345, true));
        domiciliarioRepository.save(new Domiciliario("Luca Conti", 31123496, true));
        domiciliarioRepository.save(new Domiciliario("Roberto Marini", 39915687, false));

        //Inicio de los operadores
        operadorRepository.save(new Operador( "Carlos Gómez", "carlosg", "clave123"));
        operadorRepository.save(new Operador( "María López", "marial", "clave456"));
        operadorRepository.save(new Operador( "Pedro Ramírez", "pedror", "clave789"));
        operadorRepository.save(new Operador( "Ana Martínez", "anam", "clave101"));
        operadorRepository.save(new Operador( "Luisa Fernández", "luisaf", "clave112"));
        operadorRepository.save(new Operador( "Jorge Díaz", "jorged", "clave131"));

        //Inicio de los pedidos
        pedidoRepository.save(new Pedido(1, 1, true, "2023-10-01"));
        pedidoRepository.save(new Pedido(2, 2, false, "2023-10-02"));
        pedidoRepository.save(new Pedido(3, 4, true, "2023-10-03"));
        pedidoRepository.save(new Pedido(4, 6, false, "2023-10-04"));
        pedidoRepository.save(new Pedido(5, 5, true, "2023-10-05"));
    }
    
}

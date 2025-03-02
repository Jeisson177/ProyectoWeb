package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import com.example.demo.repository.ProductoRepository;

import jakarta.transaction.Transactional;


@Controller
@Transactional
public class DatabaseInit implements ApplicationRunner{

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        productoRepository.save(new Producto( 101, "Lasagna Boloñesa", 20000, "Lasagna Boloñesa hecha con capas de pasta fresca, carne molida cocinada en una rica salsa de tomate y especias italianas, acompañada de pan artesanal y queso parmesano derretido.", "Plato Principal"));
        productoRepository.save(new Producto( 102, "Spaghetti Carbonara", 18000, "Spaghetti con auténtica salsa carbonara preparada con huevo, queso pecorino y tocino crujiente. Un plato clásico de Roma con un toque cremoso y sabor ahumado.", "Plato Principal"));
        productoRepository.save(new Producto( 103, "Pizza Margherita", 15000, "Pizza clásica napolitana con una base delgada y crujiente, cubierta con salsa de tomate casera, mozzarella fresca y hojas de albahaca para un sabor auténtico.", "Plato Principal"));
        productoRepository.save(new Producto( 104, "Ravioli de Ricotta y Espinacas", 22000, "Ravioli rellenos de suave ricotta y espinacas frescas, servidos con una delicada salsa de tomate y albahaca. Ideal para quienes buscan un plato ligero y delicioso.", "Plato Principal"));
        productoRepository.save(new Producto( 105, "Penne al Pesto", 17000, "Penne con salsa pesto elaborada con albahaca fresca, ajo, piñones tostados, queso parmesano y aceite de oliva extra virgen. Un plato aromático y lleno de sabor.", "Plato Principal"));
        productoRepository.save(new Producto( 106, "Tiramisu", 12000, "Clásico postre italiano elaborado con capas de bizcocho empapado en café espresso, crema de mascarpone suave y un toque de cacao en polvo. Perfecto para los amantes del dulce.", "Postre"));
        productoRepository.save(new Producto( 107, "Risotto de Champiñones", 19000, "Risotto cremoso cocinado con arroz arborio, champiñones frescos y queso parmesano. Su textura suave y su sabor terroso lo hacen irresistible.", "Plato Principal"));
        productoRepository.save(new Producto( 108, "Calzone", 16000, "Pizza doblada y rellena de jamón, queso mozzarella fundido y salsa de tomate, horneada hasta obtener una corteza dorada y crujiente. Un bocado de Italia en cada mordida.", "Plato Principal"));
        productoRepository.save(new Producto(109, "Fettuccine Alfredo", 21000, "Fettuccine con una salsa cremosa de queso parmesano y mantequilla. Un plato sencillo pero lleno de sabor, perfecto para los amantes de la pasta cremosa.", "Plato Principal"));
        productoRepository.save(new Producto( 110, "Cannoli Siciliani", 10000, "Postre típico siciliano de masa crujiente rellena de ricotta dulce, trozos de chocolate y fruta confitada. Una combinación de texturas y sabores irresistibles.", "Postre"));
        productoRepository.save(new Producto( 111, "Bruschetta", 8000, "Pan tostado cubierto con tomate fresco, ajo picado, albahaca y un toque de aceite de oliva virgen extra. Un aperitivo ligero y lleno de sabor mediterráneo.", "Entrada"));
        productoRepository.save(new Producto( 112, "Caprese Salad", 13000, "Ensalada fresca de tomate maduro, mozzarella de búfala y hojas de albahaca, aderezada con aceite de oliva virgen extra y balsámico. Una explosión de frescura.", "Entrada"));
        productoRepository.save(new Producto( 113, "Gnocchi de Papa", 18000, "Gnocchi caseros elaborados con papa suave y harina, servidos con una salsa de tomate casera o pesto. Una opción reconfortante y deliciosa.", "Plato Principal"));
        productoRepository.save(new Producto( 114, "Pizza Pepperoni", 17000, "Pizza con una base crujiente, salsa de tomate, queso mozzarella fundido y rodajas de pepperoni dorado. Un clásico irresistible con el toque picante del pepperoni.", "Plato Principal"));
        productoRepository.save(new Producto(115, "Ossobuco", 25000, "Estofado de jarrete de ternera cocinado lentamente con vino blanco, verduras y hierbas aromáticas. Se sirve con gremolata y un acompañamiento de risotto alla milanese.", "Plato Principal"));
        productoRepository.save(new Producto( 116, "Panna Cotta", 9000, "Postre italiano cremoso y suave, elaborado con nata, vainilla y azúcar, acompañado de una salsa de frutos rojos frescos. Un final dulce y elegante.", "Postre"));
        productoRepository.save(new Producto( 117, "Minestrone", 12000, "Sopa de verduras de temporada con pasta y frijoles, cocinada con hierbas aromáticas y un toque de tomate. Saludable y reconfortante.", "Entrada"));
        productoRepository.save(new Producto( 118, "Arancini", 11000, "Bolitas de arroz rellenas de queso y ragú, empanizadas y fritas hasta quedar doradas y crujientes. Un aperitivo tradicional siciliano lleno de sabor.", "Entrada"));
        productoRepository.save(new Producto( 119, "Zuppa Toscana", 14000, "Sopa toscana con patatas, kale y chorizo en un caldo cremoso con un toque de ajo. Calidez y sabor en cada cucharada.", "Entrada"));
        productoRepository.save(new Producto( 120, "Pizza Quattro Formaggi", 19000, "Pizza con una mezcla de cuatro quesos: mozzarella, gorgonzola, parmesano y fontina. Un placer cremoso y lleno de sabor.", "Plato Principal"));
        productoRepository.save(new Producto( 121, "Tortellini en Brodo", 16000, "Tortellini rellenos de carne en un caldo de pollo aromático. Una sopa tradicional italiana ideal para días fríos.", "Plato Principal"));
        productoRepository.save(new Producto( 122, "Carpaccio", 15000, "Láminas finas de carne cruda marinadas con aceite de oliva, limón y queso parmesano. Un plato ligero y elegante.", "Entrada"));
        productoRepository.save(new Producto( 123, "Gelato", 7000, "Helado artesanal italiano con opciones de vainilla, chocolate o fresa. Textura cremosa y sabores intensos.", "Postre"));
        productoRepository.save(new Producto( 124, "Pizza Prosciutto", 18000, "Pizza con jamón crudo (prosciutto) y rúcula fresca sobre una base crujiente con queso mozzarella. Una combinación deliciosa de sabores.", "Plato Principal"));
        productoRepository.save(new Producto( 125, "Linguini Frutti di Mare", 23000, "Linguini con mariscos frescos en una salsa de tomate picante. Un plato marino lleno de sabor mediterráneo.", "Plato Principal"));
        productoRepository.save(new Producto( 126, "Panettone", 14000, "Pan dulce italiano con frutas confitadas y pasas. Esponjoso y perfecto para acompañar con un café o té.", "Postre"));
        productoRepository.save(new Producto( 127, "Espresso", 4000, "Café espresso italiano intenso y aromático, preparado con granos de café recién molidos. Perfecto para los amantes del café fuerte y concentrado.", "Bebida"));
        productoRepository.save(new Producto( 128, "Limoncello", 9000, "Licor italiano de limón elaborado artesanalmente con cáscaras de limón y alcohol puro. Refrescante y dulce, ideal como digestivo después de una comida.", "Bebida"));
        productoRepository.save(new Producto( 129, "Affogato", 8000, "Postre simple pero delicioso, hecho con una bola de helado de vainilla bañada con un shot caliente de espresso italiano. Contraste perfecto de frío y calor.", "Postre"));
        productoRepository.save(new Producto( 130, "Cappuccino", 5000, "Café cappuccino con una base de espresso, leche vaporizada y espuma de leche espesa. Un clásico italiano cremoso y suave, perfecto para las mañanas.", "Bebida"));
    }
    
}

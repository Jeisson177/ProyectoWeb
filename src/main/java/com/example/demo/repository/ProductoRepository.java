package com.example.demo.repository;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Producto;

@Repository
public class ProductoRepository {

    private Map<Integer, Producto> productos = new HashMap<>();

    public ProductoRepository() {
        // Inicializamos la base de datos falsa con 30 productos de comida italiana
        productos.put(1, new Producto(1, 101, "Lasagna Boloñesa", 20000, "Lasagna Boloñesa hecha con capas de pasta fresca, carne molida cocinada en una rica salsa de tomate y especias italianas, acompañada de pan artesanal y queso parmesano derretido.", "Plato Principal"));
        productos.put(2, new Producto(2, 102, "Spaghetti Carbonara", 18000, "Spaghetti con auténtica salsa carbonara preparada con huevo, queso pecorino y tocino crujiente. Un plato clásico de Roma con un toque cremoso y sabor ahumado.", "Plato Principal"));
        productos.put(3, new Producto(3, 103, "Pizza Margherita", 15000, "Pizza clásica napolitana con una base delgada y crujiente, cubierta con salsa de tomate casera, mozzarella fresca y hojas de albahaca para un sabor auténtico.", "Plato Principal"));
        productos.put(4, new Producto(4, 104, "Ravioli de Ricotta y Espinacas", 22000, "Ravioli rellenos de suave ricotta y espinacas frescas, servidos con una delicada salsa de tomate y albahaca. Ideal para quienes buscan un plato ligero y delicioso.", "Plato Principal"));
        productos.put(5, new Producto(5, 105, "Penne al Pesto", 17000, "Penne con salsa pesto elaborada con albahaca fresca, ajo, piñones tostados, queso parmesano y aceite de oliva extra virgen. Un plato aromático y lleno de sabor.", "Plato Principal"));
        productos.put(6, new Producto(6, 106, "Tiramisu", 12000, "Clásico postre italiano elaborado con capas de bizcocho empapado en café espresso, crema de mascarpone suave y un toque de cacao en polvo. Perfecto para los amantes del dulce.", "Postre"));
        productos.put(7, new Producto(7, 107, "Risotto de Champiñones", 19000, "Risotto cremoso cocinado con arroz arborio, champiñones frescos y queso parmesano. Su textura suave y su sabor terroso lo hacen irresistible.", "Plato Principal"));
        productos.put(8, new Producto(8, 108, "Calzone", 16000, "Pizza doblada y rellena de jamón, queso mozzarella fundido y salsa de tomate, horneada hasta obtener una corteza dorada y crujiente. Un bocado de Italia en cada mordida.", "Plato Principal"));
        productos.put(9, new Producto(9, 109, "Fettuccine Alfredo", 21000, "Fettuccine con una salsa cremosa de queso parmesano y mantequilla. Un plato sencillo pero lleno de sabor, perfecto para los amantes de la pasta cremosa.", "Plato Principal"));
        productos.put(10, new Producto(10, 110, "Cannoli Siciliani", 10000, "Postre típico siciliano de masa crujiente rellena de ricotta dulce, trozos de chocolate y fruta confitada. Una combinación de texturas y sabores irresistibles.", "Postre"));
        productos.put(11, new Producto(11, 111, "Bruschetta", 8000, "Pan tostado cubierto con tomate fresco, ajo picado, albahaca y un toque de aceite de oliva virgen extra. Un aperitivo ligero y lleno de sabor mediterráneo.", "Entrada"));
        productos.put(12, new Producto(12, 112, "Caprese Salad", 13000, "Ensalada fresca de tomate maduro, mozzarella de búfala y hojas de albahaca, aderezada con aceite de oliva virgen extra y balsámico. Una explosión de frescura.", "Entrada"));
        productos.put(13, new Producto(13, 113, "Gnocchi de Papa", 18000, "Gnocchi caseros elaborados con papa suave y harina, servidos con una salsa de tomate casera o pesto. Una opción reconfortante y deliciosa.", "Plato Principal"));
        productos.put(14, new Producto(14, 114, "Pizza Pepperoni", 17000, "Pizza con una base crujiente, salsa de tomate, queso mozzarella fundido y rodajas de pepperoni dorado. Un clásico irresistible con el toque picante del pepperoni.", "Plato Principal"));
        productos.put(15, new Producto(15, 115, "Ossobuco", 25000, "Estofado de jarrete de ternera cocinado lentamente con vino blanco, verduras y hierbas aromáticas. Se sirve con gremolata y un acompañamiento de risotto alla milanese.", "Plato Principal"));
        productos.put(16, new Producto(16, 116, "Panna Cotta", 9000, "Postre italiano cremoso y suave, elaborado con nata, vainilla y azúcar, acompañado de una salsa de frutos rojos frescos. Un final dulce y elegante.", "Postre"));
        productos.put(17, new Producto(17, 117, "Minestrone", 12000, "Sopa de verduras de temporada con pasta y frijoles, cocinada con hierbas aromáticas y un toque de tomate. Saludable y reconfortante.", "Entrada"));
        productos.put(18, new Producto(18, 118, "Arancini", 11000, "Bolitas de arroz rellenas de queso y ragú, empanizadas y fritas hasta quedar doradas y crujientes. Un aperitivo tradicional siciliano lleno de sabor.", "Entrada"));
        productos.put(19, new Producto(19, 119, "Zuppa Toscana", 14000, "Sopa toscana con patatas, kale y chorizo en un caldo cremoso con un toque de ajo. Calidez y sabor en cada cucharada.", "Entrada"));
        productos.put(20, new Producto(20, 120, "Pizza Quattro Formaggi", 19000, "Pizza con una mezcla de cuatro quesos: mozzarella, gorgonzola, parmesano y fontina. Un placer cremoso y lleno de sabor.", "Plato Principal"));
        productos.put(21, new Producto(21, 121, "Tortellini en Brodo", 16000, "Tortellini rellenos de carne en un caldo de pollo aromático. Una sopa tradicional italiana ideal para días fríos.", "Plato Principal"));
        productos.put(22, new Producto(22, 122, "Carpaccio", 15000, "Láminas finas de carne cruda marinadas con aceite de oliva, limón y queso parmesano. Un plato ligero y elegante.", "Entrada"));
        productos.put(23, new Producto(23, 123, "Gelato", 7000, "Helado artesanal italiano con opciones de vainilla, chocolate o fresa. Textura cremosa y sabores intensos.", "Postre"));
        productos.put(24, new Producto(24, 124, "Pizza Prosciutto", 18000, "Pizza con jamón crudo (prosciutto) y rúcula fresca sobre una base crujiente con queso mozzarella. Una combinación deliciosa de sabores.", "Plato Principal"));
        productos.put(25, new Producto(25, 125, "Linguini Frutti di Mare", 23000, "Linguini con mariscos frescos en una salsa de tomate picante. Un plato marino lleno de sabor mediterráneo.", "Plato Principal"));
        productos.put(26, new Producto(26, 126, "Panettone", 14000, "Pan dulce italiano con frutas confitadas y pasas. Esponjoso y perfecto para acompañar con un café o té.", "Postre"));
        productos.put(27, new Producto(27, 127, "Espresso", 4000, "Café espresso italiano intenso y aromático, preparado con granos de café recién molidos. Perfecto para los amantes del café fuerte y concentrado.", "Bebida"));
        productos.put(28, new Producto(28, 128, "Limoncello", 9000, "Licor italiano de limón elaborado artesanalmente con cáscaras de limón y alcohol puro. Refrescante y dulce, ideal como digestivo después de una comida.", "Bebida"));
        productos.put(29, new Producto(29, 129, "Affogato", 8000, "Postre simple pero delicioso, hecho con una bola de helado de vainilla bañada con un shot caliente de espresso italiano. Contraste perfecto de frío y calor.", "Postre"));
        productos.put(30, new Producto(30, 130, "Cappuccino", 5000, "Café cappuccino con una base de espresso, leche vaporizada y espuma de leche espesa. Un clásico italiano cremoso y suave, perfecto para las mañanas.", "Bebida"));
    
    }
    
    

    public Producto getProducto(int id) {
        return productos.get(id);
    }

    public Map<Integer, Producto> getAllProductos() {
        return productos;
    }

    // Método para agregar un nuevo producto
    public void addProducto(Producto producto) {
        int newId = productos.size() + 1;
        producto.setProducto_ID(newId);
        productos.put(newId, producto);
    }

    // Método para actualizar un producto existente
    public void updateProducto(int id, Producto producto) {
        if (productos.containsKey(id)) {
            producto.setProducto_ID(id);
            productos.put(id, producto);
        }
    }

    // Método para eliminar un producto por su ID
    public void deleteProducto(int id) {
        productos.remove(id);
    }


}
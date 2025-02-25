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
        productos.put(1, new Producto(1, 101, "Lasagna Boloñesa", 20000, "Lasagna Boloñesa acompañada de pan y queso", "Plato Principal"));
        productos.put(2, new Producto(2, 102, "Spaghetti Carbonara", 18000, "Spaghetti con salsa carbonara hecha con huevo, queso y tocino", "Plato Principal"));
        productos.put(3, new Producto(3, 103, "Pizza Margherita", 15000, "Pizza clásica con tomate, mozzarella y albahaca", "Plato Principal"));
        productos.put(4, new Producto(4, 104, "Ravioli de Ricotta y Espinacas", 22000, "Ravioli relleno de ricotta y espinacas con salsa de tomate", "Plato Principal"));
        productos.put(5, new Producto(5, 105, "Penne al Pesto", 17000, "Penne con salsa pesto de albahaca, ajo y piñones", "Plato Principal"));
        productos.put(6, new Producto(6, 106, "Tiramisú", 12000, "Postre italiano con café, cacao y queso mascarpone", "Postre"));
        productos.put(7, new Producto(7, 107, "Risotto de Champiñones", 19000, "Risotto cremoso con champiñones y queso parmesano", "Plato Principal"));
        productos.put(8, new Producto(8, 108, "Calzone", 16000, "Pizza doblada rellena de jamón, queso y tomate", "Plato Principal"));
        productos.put(9, new Producto(9, 109, "Fettuccine Alfredo", 21000, "Fettuccine con salsa cremosa de queso parmesano y mantequilla", "Plato Principal"));
        productos.put(10, new Producto(10, 110, "Cannoli Siciliani", 10000, "Postre de masa crujiente rellena de ricotta y chocolate", "Postre"));
        productos.put(11, new Producto(11, 111, "Bruschetta", 8000, "Pan tostado con tomate, ajo y aceite de oliva", "Entrada"));
        productos.put(12, new Producto(12, 112, "Caprese Salad", 13000, "Ensalada de tomate, mozzarella y albahaca", "Entrada"));
        productos.put(13, new Producto(13, 113, "Gnocchi de Papa", 18000, "Gnocchi casero con salsa de tomate o pesto", "Plato Principal"));
        productos.put(14, new Producto(14, 114, "Pizza Pepperoni", 17000, "Pizza con pepperoni y queso mozzarella", "Plato Principal"));
        productos.put(15, new Producto(15, 115, "Ossobuco", 25000, "Estofado de carne con verduras y vino blanco", "Plato Principal"));
        productos.put(16, new Producto(16, 116, "Panna Cotta", 9000, "Postre cremoso con salsa de frutos rojos", "Postre"));
        productos.put(17, new Producto(17, 117, "Minestrone", 12000, "Sopa de verduras con pasta y frijoles", "Entrada"));
        productos.put(18, new Producto(18, 118, "Arancini", 11000, "Bolitas de arroz rellenas de queso y ragú", "Entrada"));
        productos.put(19, new Producto(19, 119, "Zuppa Toscana", 14000, "Sopa toscana con patatas, kale y chorizo", "Entrada"));
        productos.put(20, new Producto(20, 120, "Pizza Quattro Formaggi", 19000, "Pizza con cuatro quesos: mozzarella, gorgonzola, parmesano y fontina", "Plato Principal"));
        productos.put(21, new Producto(21, 121, "Tortellini en Brodo", 16000, "Tortellini rellenos de carne en caldo de pollo", "Plato Principal"));
        productos.put(22, new Producto(22, 122, "Carpaccio", 15000, "Láminas de carne cruda con aceite de oliva y queso parmesano", "Entrada"));
        productos.put(23, new Producto(23, 123, "Gelato", 7000, "Helado italiano de vainilla, chocolate o fresa", "Postre"));
        productos.put(24, new Producto(24, 124, "Pizza Prosciutto", 18000, "Pizza con jamón crudo y rúcula", "Plato Principal"));
        productos.put(25, new Producto(25, 125, "Linguini Frutti di Mare", 23000, "Linguini con mariscos en salsa de tomate", "Plato Principal"));
        productos.put(26, new Producto(26, 126, "Panettone", 14000, "Pan dulce italiano con frutas confitadas", "Postre"));
        productos.put(27, new Producto(27, 127, "Espresso", 4000, "Café espresso italiano", "Bebida"));
        productos.put(28, new Producto(28, 128, "Limoncello", 9000, "Licor italiano de limón", "Bebida"));
        productos.put(29, new Producto(29, 129, "Affogato", 8000, "Helado de vainilla con un shot de espresso", "Postre"));
        productos.put(30, new Producto(30, 130, "Cappuccino", 5000, "Café cappuccino con espuma de leche", "Bebida"));
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
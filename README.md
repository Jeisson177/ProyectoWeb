# 🍝 Pasta e Passione - Sistema de Gestión de Restaurante

Este es un sistema de gestión de restaurante desarrollado en **Java con Spring Boot**, que permite administrar clientes, pedidos, productos y personal. Se utiliza una base de datos en memoria **H2** para facilitar el desarrollo y pruebas.

## 📁 Estructura del Proyecto

```plaintext
Pasta-e-Passione
│── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── controller/        # Controladores de la API
│   │   │   ├── entity/            # Modelos de datos (Entidades)
│   │   │   ├── repository/        # Acceso a la base de datos con JPA
│   │   │   ├── service/           # Lógica de negocio
│   │   │   ├── DemoApplication.java  # Punto de entrada de la aplicación
│   │   ├── resources/
│   │   │   ├── static/            # CSS, JS, imágenes, videos
│   │   │   ├── templates/         # Vistas HTML con Thymeleaf
│   │   │   ├── application.properties  # Configuración de la aplicación
│   ├── README.md  # Documentación del proyecto
│── .mvn/          # Archivos de configuración de Maven
│── .vscode/       # Configuración del entorno en VS Code
│── pom.xml        # Dependencias de Maven
```

## 🚀 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
  - Spring MVC (Controladores y manejo de rutas)
  - Spring Data JPA (Gestión de base de datos)
  - Spring Security (Autenticación y roles)
- **Thymeleaf** (Motor de plantillas HTML)
- **H2 Database** (Base de datos en memoria)
- **Maven** (Gestión de dependencias)
- **Bootstrap / CSS** (Diseño de interfaz)
- **JavaScript** (Interactividad en el frontend)

## ⚙️ Instalación y Ejecución

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/Jeisson177/ProyectoWeb.git
Configurar la base de datos (H2)
El proyecto usa H2 en memoria, por lo que no necesitas instalar una base de datos externa. En application.properties, la configuración es la siguiente:

properties
server.port=8000
spring.web.resources.static-locations=classpath:/static/
#datasource
spring.jpa.database=h2
spring.datasource.driver-class-name=org.h2.Driver
#nombre base de datos
spring.datasource.url=jdbc:h2:file:./mydatabase
spring.datasource.username=sa
spring.datasource.password=
#consola de administracion de h2
spring.h2.console.enabled=true
#localhost:8000/h2
spring.h2.console.path=/h2
#Datos de prueba
spring.jpa.generate-ddl=true
#borrar la tabla cada vez
spring.jpa.hibernate.ddl-auto=create-drop
#spring.sql.init.mode=

#Logging
#permite imrpmir la query
logging.level.org.hibernate.SQL=DEBUG
#imprime los parametros de las querys
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

#Para que muestre la pagina de error personalizada
server.error.whitelabel.enabled=false
server.error.path=/error

spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.enabled=true
Compilar y ejecutar la aplicación


mvn clean install
mvn spring-boot:run
Acceder a la aplicación

🌐 Interfaz Web: http://localhost:8000
📂 Consola H2 (para consultar la base de datos): http://localhost:8000/h2
JDBC URL: jdbc:h2:file:./mydatabase
Usuario: sa
Contraseña: (dejar en blanco)
📌 Funcionalidades
✅ Gestión de Clientes: Registro, edición y eliminación de clientes.
✅ Gestión de Pedidos: Creación, actualización y listado de pedidos.
✅ Gestión de Productos: CRUD de productos en el menú.
✅ Interfaz Web Dinámica: Uso de Thymeleaf para las vistas y Bootstrap para el diseño.
✅ Autenticación : Inicio de sesión para administradores, empleados y clientes.

🛠 Estructura de Paquetes
controller/: Controladores para gestionar solicitudes HTTP.
entity/: Clases que representan las tablas en la base de datos.
repository/: Interfaces que extienden JpaRepository para interactuar con la base de datos.
service/: Implementaciones de la lógica de negocio.
resources/:
static/: Archivos CSS, JavaScript e imágenes.
templates/: Plantillas HTML con Thymeleaf.
application.properties: Configuración de la aplicación.
📜 Autores
Jeisson Camilo Ruiz Cristancho - Desarrollador Backend
Nicolas Alberto Pedraza Vasques - Desarrollador Backend
Mariana -Desarrollador Frontend
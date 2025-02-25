// Función para cargar el JSON y generar el menú
async function cargarMenu() {
    try {
        // Obtener el JSON desde el endpoint de Spring Boot
        const respuesta = await fetch('/api/productos');
        const datos = await respuesta.json();
        
        const contenedorMenu = document.getElementById('menu-contenedor');
        
        // Objeto para agrupar los productos por categorías
        const categorias = {
            "Entrada": [],
            "Plato Principal": [],
            "Postre": [],
            "Bebida": []
        };

        // Agrupamos los productos por categoría
        Object.values(datos).forEach(producto => {
            if (categorias[producto.categoria]) {
                categorias[producto.categoria].push(producto);
            }
        });

        // Generar el menú dinámicamente por categoría
        Object.keys(categorias).forEach(categoria => {
            // Verificamos si hay productos en la categoría
            if (categorias[categoria].length > 0) {
                // Crear la sección de la categoría con estilo de .portada
                const seccion = document.createElement('div');
                seccion.classList.add('portada');
                seccion.innerHTML = `<section>
                                        <h2>${categoria}</h2>
                                        <ul></ul>
                                    </section>`;

                // Añadir productos a la categoría
                categorias[categoria].forEach(producto => {
                    // Convertimos el nombre del producto en un nombre de archivo amigable
                    const nombreImagen = producto.nombre.toLowerCase().replace(/ /g, '-') + '.jpg';
                    // Definimos la ruta de la imagen usando la categoría
                    const categoriaCarpeta = categoria.toLowerCase().replace(/ /g, '');
                    const rutaImagen = `/Imagenes/menu/${categoriaCarpeta}/${nombreImagen}`;

                    const li = document.createElement('li');
                    li.innerHTML = `
                        <img src="${rutaImagen}" alt="${producto.nombre}" class="producto-imagen">
                        <h3>${producto.nombre}</h3>
                        <p>${producto.descripcion}</p>
                        <span>${producto.precio}</span>
                    `;
                    seccion.querySelector('ul').appendChild(li);
                });

                // Añadir la sección al contenedor principal
                contenedorMenu.appendChild(seccion);
            }
        });
    } catch (error) {
        console.error('Error cargando el menú:', error);
    }
}

// Ejecutar al cargar la página
window.addEventListener('load', cargarMenu);

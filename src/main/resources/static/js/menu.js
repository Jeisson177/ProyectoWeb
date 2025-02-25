async function cargarMenu() {
    try {
        const respuesta = await fetch('/api/menu');
        const datos = await respuesta.json();

        console.log("Datos recibidos:", datos);

        // Convertir los datos en un array de productos
        const productosArray = Object.values(datos);
        console.log("Array de productos:", productosArray);

        // Objeto para agrupar los productos por categorías con claves normalizadas
        const categorias = {
            "entrada": [],
            "platoprincipal": [],
            "postre": [],
            "bebida": []
        };

        // Agrupar los productos correctamente
        productosArray.forEach(producto => {
            if (!producto.tipo) {
                console.warn(`Producto sin tipo: ${JSON.stringify(producto)}`);
                return; // Evitamos errores
            }

            // Normalizar el tipo para coincidir con las claves de `categorias`
            const tipoNormalizado = producto.tipo.trim().toLowerCase().replace(/\s+/g, '');
            console.log(`Tipo normalizado: '${tipoNormalizado}'`);

            if (categorias[tipoNormalizado]) {
                console.log(`Agregando producto: ${producto.nombre} a ${tipoNormalizado}`);
                categorias[tipoNormalizado].push(producto);
            } else {
                console.warn(`No se encontró la categoría para: '${producto.tipo}'`);
            }
        });

        // Verificar cuántos productos hay en cada categoría
        Object.keys(categorias).forEach(categoria => {
            console.log(`Categoría: ${categoria} - Productos: ${categorias[categoria].length}`);
        });

        // Generar el menú dinámicamente
        const contenedorMenu = document.getElementById('menu-contenedor');
        Object.keys(categorias).forEach(categoria => {
            if (categorias[categoria].length > 0) {
                const seccion = document.createElement('div');
                seccion.classList.add('portada');
                seccion.innerHTML = `<section>
                                        <h2>${categoria}</h2>
                                        <ul></ul>
                                    </section>`;

                categorias[categoria].forEach(producto => {
                    console.log(`Renderizando producto: ${producto.nombre}`);

                    const nombreImagen = producto.nombre.toLowerCase().replace(/ /g, '-') + '.jpg';
                    const rutaImagen = `/Imagenes/menu/${categoria}/${nombreImagen}`;

                    const precioFormateado = new Intl.NumberFormat('es-CO', {
                        style: 'currency',
                        currency: 'COP'
                    }).format(producto.precio);

                    const li = document.createElement('li');
                    li.innerHTML = `
                        <img src="${rutaImagen}" alt="${producto.nombre}" class="producto-imagen">
                        <h3>${producto.nombre}</h3>
                        <p>${producto.descripcion}</p>
                        <span>${precioFormateado}</span>
                    `;
                    seccion.querySelector('ul').appendChild(li);
                });

                contenedorMenu.appendChild(seccion);
            }
        });

    } catch (error) {
        console.error("Error al obtener el menú:", error);
    }
}

window.addEventListener('load', cargarMenu);

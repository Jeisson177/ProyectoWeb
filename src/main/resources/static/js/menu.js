async function cargarMenu() {
    try {
        const respuesta = await fetch('/api/productos');
        const productos = await respuesta.json();

        const categorias = {
            "entrada": [],
            "platoprincipal": [],
            "postre": [],
            "bebida": []
        };

        productos.forEach(producto => {
            const tipoNormalizado = producto.tipo.trim().toLowerCase().replace(/\s+/g, '');

            if (categorias[tipoNormalizado]) {
                categorias[tipoNormalizado].push(producto);
            }
        });

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
                    const nombreImagen = producto.nombre.toLowerCase().replace(/ /g, '-') + '.jpg';
                    const rutaImagen = `/Imagenes/menu/${categoria}/${nombreImagen}`;
                    
                    const precioFormateado = new Intl.NumberFormat('es-CO', {
                        style: 'currency',
                        currency: 'COP'
                    }).format(producto.precio);

                    const li = document.createElement('li');
                    li.innerHTML = `
                        <img src="${rutaImagen}" alt="${producto.nombre}" class="producto-imagen" data-id="${producto.producto_ID}">
                        <h3>${producto.nombre}</h3>
                        <p>${producto.descripcion}</p>
                        <span>${precioFormateado}</span>
                    `;

                    // Evento para redirigir al detalle del producto
                    li.querySelector('.producto-imagen').addEventListener('click', function () {
                        const id = this.dataset.id;
                        window.location.href = `detalle.html?id=${id}`;
                    });

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

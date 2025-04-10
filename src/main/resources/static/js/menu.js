async function cargarMenu() {
    try {
        const respuesta = await fetch('/producto'); // Llamada a la API
        const productos = await respuesta.json();
        console.log("Productos recibidos:", productos.length);

        const categorias = {
            "entrada": [],
            "platoprincipal": [],
            "postre": [],
            "bebida": []
        };

        let carrito = JSON.parse(localStorage.getItem("carrito")) || [];
        if (!Array.isArray(carrito)) {
            carrito = []; // Si no es un array, lo inicializamos correctamente
        }

        productos.forEach(producto => {
            // Normalizar la categoría (convertir a minúsculas y eliminar espacios extra)
            const categoriaNormalizada = producto.categoria.trim().toLowerCase().replace(/\s+/g, '');
            
            if (categorias[categoriaNormalizada]) {
                categorias[categoriaNormalizada].push(producto);
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
                    console.log("Imagen del producto:", rutaImagen);

                    const precioFormateado = new Intl.NumberFormat('es-CO', {
                        style: 'currency',
                        currency: 'COP'
                    }).format(producto.precio);

                    const li = document.createElement('li');
                    li.innerHTML = `
                        <img src="${rutaImagen}" alt="${producto.nombre}" class="producto-imagen" data-id="${producto.producto_id}">
                        <h3>${producto.nombre}</h3>
                        <p>${producto.descripcion}</p>
                        <span>${precioFormateado}</span>
                        <button class="agregar-carrito" 
                                data-id="${producto.producto_id}" 
                                data-nombre="${producto.nombre}" 
                                data-precio="${producto.precio}" 
                                data-imagen="${rutaImagen}">+</button>
                    `;

                    // ✅ Evento corregido para redirigir al detalle del producto
                    li.querySelector('.producto-imagen').addEventListener('click', function () {
                        const id = this.dataset.id;
                        console.log("Redirigiendo al producto con ID:", id);
                        if (id) {
                            window.location.href = `/producto/${id}`;
                        } else {
                            console.error("Error: ID de producto no definido");
                        }
                    });

                    // ✅ Evento para agregar al carrito
                    li.querySelector('.agregar-carrito').addEventListener('click', function () {
                        const idProducto = this.dataset.id;
                        const nombre = this.dataset.nombre;
                        const precio = parseFloat(this.dataset.precio);
                        const imagen = this.dataset.imagen;
                        agregarAlCarrito(idProducto, nombre, precio, imagen);
                    });

                    seccion.querySelector('ul').appendChild(li);
                });

                contenedorMenu.appendChild(seccion);
            }
        });

        actualizarCarritoUI(); // Actualiza la UI del carrito al cargar

    } catch (error) {
        console.error("Error al obtener el menú:", error);
    }
}

// Función para agregar productos al carrito
function agregarAlCarrito(id, nombre, precio, imagen) {
    let carrito = JSON.parse(localStorage.getItem("carrito")) || [];
    
    const productoExistente = carrito.find(item => item.id === id);

    if (productoExistente) {
        productoExistente.cantidad++;
    } else {
        carrito.push({ id, nombre, precio, imagen, cantidad: 1 });
    }

    localStorage.setItem("carrito", JSON.stringify(carrito));
    actualizarCarritoUI();
}

// Función para actualizar la UI del carrito
function actualizarCarritoUI() {
    const listaCarrito = document.getElementById("lista-carrito");
    const contadorCarrito = document.getElementById("contador-carrito");

    listaCarrito.innerHTML = ""; // Limpiar antes de renderizar

    let totalCantidad = 0;
    let carrito = JSON.parse(localStorage.getItem("carrito")) || [];

    carrito.forEach(producto => {
        totalCantidad += producto.cantidad;

        const li = document.createElement("li");
        li.innerHTML = `
            <img src="${producto.imagen}" alt="${producto.nombre}" width="50">
            <span>${producto.nombre} x${producto.cantidad}</span>
            <button class="eliminar-item" data-id="${producto.id}">X</button>
        `;

        li.querySelector(".eliminar-item").addEventListener("click", function () {
            eliminarDelCarrito(producto.id);
        });

        listaCarrito.appendChild(li);
    });

    contadorCarrito.textContent = totalCantidad;
}

// Función para eliminar un producto del carrito
function eliminarDelCarrito(id) {
    let carrito = JSON.parse(localStorage.getItem("carrito")) || [];
    carrito = carrito.filter(item => item.id !== id);
    localStorage.setItem("carrito", JSON.stringify(carrito));
    actualizarCarritoUI();
}

// Función para vaciar el carrito
function vaciarCarrito() {
    localStorage.setItem("carrito", JSON.stringify([]));
    actualizarCarritoUI();
}

// Evento para vaciar el carrito
document.getElementById("vaciar-carrito").addEventListener("click", vaciarCarrito);

// Mostrar/ocultar carrito al hacer hover
document.getElementById("carrito-container").addEventListener("mouseenter", function () {
    document.getElementById("carrito").classList.remove("carrito-oculto");
});

document.getElementById("carrito").addEventListener("mouseleave", function () {
    document.getElementById("carrito").classList.add("carrito-oculto");
});

// Cargar el menú cuando la página se carga
window.addEventListener('load', cargarMenu);

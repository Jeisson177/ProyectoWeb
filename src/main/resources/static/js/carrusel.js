// Seleccionar elementos del carrusel
const caruselContainer = document.querySelector('.carusel-container');
const caruselItems = document.querySelectorAll('.carusel-item');
const prevButton = document.querySelector('.prev');
const nextButton = document.querySelector('.next');

// Configuración inicial
let currentIndex = 0;
let totalItems = caruselItems.length;
let itemWidth;
let itemGap;
let totalItemWidth;
let itemsVisible;

// Función para ajustar el tamaño de los ítems y aplicar overflow
const adjustItemWidth = () => {
    itemWidth = caruselItems[0].getBoundingClientRect().width;
    itemGap = parseInt(getComputedStyle(caruselContainer).gap) || 0;
    totalItemWidth = itemWidth + itemGap;
    itemsVisible = Math.floor(caruselContainer.clientWidth / totalItemWidth);

    // Si itemsVisible es 0, lo ajustamos a 1 para evitar problemas
    if (itemsVisible < 1) itemsVisible = 1;

    caruselItems.forEach(item => {
        item.style.minWidth = `${itemWidth}px`;
        item.style.overflow = 'visible'; // Aseguramos visibilidad
    });

    caruselContainer.style.overflow = 'visible'; // Aseguramos visibilidad
    console.log('Overflow ajustado a visible');
    console.log('Ancho de cada ítem: ', itemWidth);
    console.log('Espacio entre ítems: ', itemGap);
    console.log('Total ancho ítem (con gap): ', totalItemWidth);
    console.log('Ítems visibles en pantalla: ', itemsVisible);
};

// Función para actualizar la posición del carrusel
const updateCaruselPosition = () => {
    const newPosition = -(totalItemWidth * currentIndex);
    caruselContainer.style.transform = `translateX(${newPosition}px)`;
    caruselContainer.style.transition = 'transform 0.5s ease-in-out';
    console.log('Actualizando posición del carrusel. Índice actual: ', currentIndex);
};

// Función para mover hacia la izquierda
const moveToPrev = () => {
    console.log('Clic en botón PREV. Índice antes de mover: ', currentIndex);
    if (currentIndex > 0) {
        currentIndex--;
    } else {
        currentIndex = totalItems - itemsVisible; // Va al último conjunto visible
    }
    updateCaruselPosition();
    console.log('Nueva imagen mostrada. Índice actual: ', currentIndex);
};

// Función para mover hacia la derecha
const moveToNext = () => {
    console.log('Clic en botón NEXT. Índice antes de mover: ', currentIndex);
    if (currentIndex < totalItems - itemsVisible) {
        currentIndex++;
    } else {
        currentIndex = 0; // Regresa al primer conjunto
    }
    updateCaruselPosition();
    console.log('Nueva imagen mostrada. Índice actual: ', currentIndex);
};

// Ajuste del tamaño al cargar la página y al cambiar el tamaño de la ventana
window.addEventListener('load', adjustItemWidth);
window.addEventListener('resize', adjustItemWidth);

// Event listeners para los botones
prevButton.addEventListener('click', moveToPrev);
nextButton.addEventListener('click', moveToNext);

// Auto deslizar cada 5 segundos (opcional, si no lo quieres, elimínalo)
let autoSlide = setInterval(moveToNext, 5000);

// Pausar el auto deslizar cuando el mouse esté sobre el carrusel
caruselContainer.addEventListener('mouseover', () => {
    clearInterval(autoSlide);
});

caruselContainer.addEventListener('mouseout', () => {
    autoSlide = setInterval(moveToNext, 5000);
});

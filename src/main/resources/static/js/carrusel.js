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
};

// Función para actualizar la posición del carrusel
const updateCaruselPosition = () => {
    const newPosition = -(totalItemWidth * currentIndex);
    caruselContainer.style.transform = `translateX(${newPosition}px)`;
    caruselContainer.style.transition = 'transform 0.5s ease-in-out';
};

// Función para mover hacia la izquierda
const moveToPrev = () => {
    if (currentIndex > 1) {
        currentIndex--;
    } else {
        currentIndex = totalItems - itemsVisible; // Va al último conjunto visible
    }
    updateCaruselPosition();
};

// Función para mover hacia la derecha
const moveToNext = () => {
    if (currentIndex < totalItems - itemsVisible) {
        currentIndex++;
    } else {
        currentIndex = 0; // Regresa al primer conjunto
    }
    updateCaruselPosition();
};

// Ajuste del tamaño al cargar la página y al cambiar el tamaño de la ventana
window.addEventListener('load', adjustItemWidth);
window.addEventListener('resize', adjustItemWidth);

// Event listeners para los botones
prevButton.addEventListener('click', moveToPrev);
nextButton.addEventListener('click', moveToNext);

// Auto deslizar
let autoSlide = setInterval(moveToNext, 5000);

// Pausar el auto deslizar cuando el mouse esté sobre el carrusel
caruselContainer.addEventListener('mouseover', () => {
    clearInterval(autoSlide);
});

caruselContainer.addEventListener('mouseout', () => {
    autoSlide = setInterval(moveToNext, 5000);
});

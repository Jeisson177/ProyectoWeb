// Seleccionar elementos del carrusel
const caruselContainer = document.querySelector('.carusel-container');
const caruselItems = document.querySelectorAll('.carusel-item');
const prevButton = document.querySelector('.prev');
const nextButton = document.querySelector('.next');

// Configuración inicial
let currentIndex = 0;
const totalItems = caruselItems.length;
const itemWidth = caruselItems[0].clientWidth;
const itemGap = parseInt(getComputedStyle(caruselContainer).gap) || 0;
const totalItemWidth = itemWidth + itemGap;
const itemsVisible = Math.floor(caruselContainer.clientWidth / totalItemWidth);

// Clonar items para el efecto infinito
caruselItems.forEach(item => {
    const clone = item.cloneNode(true);
    caruselContainer.appendChild(clone);
});

// Función para actualizar la posición del carrusel
const updateCaruselPosition = () => {
    const newPosition = -(totalItemWidth * currentIndex);
    caruselContainer.style.transform = `translateX(${newPosition}px)`;
    caruselContainer.style.transition = 'transform 0.5s ease-in-out';
};

// Función para mover hacia la izquierda
const moveToPrev = () => {
    if (currentIndex > 0) {
        currentIndex--;
    } else {
        currentIndex = totalItems;
        caruselContainer.style.transition = 'none';
        const resetPosition = -(totalItemWidth * currentIndex);
        caruselContainer.style.transform = `translateX(${resetPosition}px)`;
        setTimeout(() => {
            caruselContainer.style.transition = 'transform 0.5s ease-in-out';
            currentIndex--;
            updateCaruselPosition();
        }, 50);
    }
    updateCaruselPosition();
};

// Función para mover hacia la derecha
const moveToNext = () => {
    currentIndex++;
    updateCaruselPosition();
    if (currentIndex === totalItems) {
        setTimeout(() => {
            caruselContainer.style.transition = 'none';
            currentIndex = 0;
            caruselContainer.style.transform = `translateX(0px)`;
        }, 500);
    }
};

// Event listeners para los botones
prevButton.addEventListener('click', moveToPrev);
nextButton.addEventListener('click', moveToNext);

// Auto deslizar cada 5 segundos
let autoSlide = setInterval(moveToNext, 5000);

// Pausar el auto deslizar cuando el mouse esté sobre el carrusel
caruselContainer.addEventListener('mouseover', () => {
    clearInterval(autoSlide);
});

// Reanudar el auto deslizar cuando el mouse salga del carrusel
caruselContainer.addEventListener('mouseout', () => {
    autoSlide = setInterval(moveToNext, 5000);
});

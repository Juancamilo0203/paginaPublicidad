// Carrusel: cambio de imagen
let index = 0;
const images = document.querySelectorAll(".carousel img");
const totalImages = images.length;

function showNextImage() {
    index = (index + 1) % totalImages;
    const carouselImages = document.querySelector(".carousel-images");
    carouselImages.style.transform = `translateX(-${index * 100}%)`;
}

function prevImage() {
    index = (index - 1 + totalImages) % totalImages;
    const carouselImages = document.querySelector(".carousel-images");
    carouselImages.style.transform = `translateX(-${index * 100}%)`;
}

function nextImage() {
    index = (index + 1) % totalImages;
    const carouselImages = document.querySelector(".carousel-images");
    carouselImages.style.transform = `translateX(-${index * 100}%)`;
}

setInterval(showNextImage, 3000);

function toggleMenu() {
    const navMenu = document.querySelector('.nav-menu');
    navMenu.classList.toggle('active');
}


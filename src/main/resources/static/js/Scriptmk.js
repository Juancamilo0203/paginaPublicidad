function confirmarRegistro(descripcion) {
    // Obtener la sección desde la URL actual
    const urlPath = window.location.pathname;
    const seccion = urlPath.split('/')[1];

    if (confirm("¿Deseas registrar este pedido?")) {
        fetch('/pedidos/registrarPedido', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: `descripcion=${encodeURIComponent(descripcion)}&seccion=${encodeURIComponent(seccion)}`
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Error en el servidor. No se pudo registrar el pedido.");
                }
                return response.text();
            })
            .then(() => {
                alert("¡Pedido registrado exitosamente!");
                location.reload();
            })
            .catch(error => {
                console.error("Error:", error);
                alert("Ocurrió un error al registrar el pedido.");
            });
    }
}


function toggleMenu() {
    const navMenu = document.querySelector('.nav-menu');
    navMenu.classList.toggle('active');
}
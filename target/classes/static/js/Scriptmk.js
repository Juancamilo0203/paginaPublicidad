function confirmarRegistro(descripcion) {
    if (confirm("¿Deseas registrar este pedido?")) {
        fetch('/pedidos/registrarPedido', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: `descripcion=${encodeURIComponent(descripcion)}`
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Error en el servidor. No se pudo registrar el pedido.");
                }
                return response.text(); // No esperamos JSON, redirecciona el servidor
            })
            .then(() => {
                alert("¡Pedido registrado exitosamente!");
                location.reload(); // Recargar la página para reflejar los cambios
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
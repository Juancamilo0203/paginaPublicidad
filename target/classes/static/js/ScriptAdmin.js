function mostrarTabla(tabla) {
    document.getElementById("tabla-usuarios").style.display = tabla === "usuarios" ? "block" : "none";
    document.getElementById("tabla-pedidos").style.display = tabla === "pedidos" ? "block" : "none";
}
function eliminarPedido(id) {
    if (confirm("¿Está seguro de que desea eliminar este pedido?")) {
        fetch(`/eliminarPedido/${id}`, {
            method: 'GET'
        })
            .then(response => {
                if (response.ok) {
                    alert("Pedido eliminado exitosamente.");
                    location.reload(); // Recarga la página para actualizar la tabla
                } else {
                    alert("Error al eliminar el pedido.");
                }
            })
            .catch(error => console.error("Error:", error));
    }
}
function editarPedido(id) {
    const descripcion = prompt("Ingrese la nueva descripción del pedido:");
    const fechaPedido = prompt("Ingrese la nueva fecha del pedido (YYYY-MM-DD):");

    if (descripcion && fechaPedido) {
        fetch(`/editarPedido`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                id: id,
                descripcion: descripcion,
                fechaPedido: fechaPedido
            })
        })
            .then(response => {
                if (response.ok) {
                    alert("Pedido editado exitosamente.");
                    location.reload(); // Recarga la página para actualizar la tabla
                } else {
                    alert("Error al editar el pedido.");
                }
            })
            .catch(error => console.error("Error:", error));
    } else {
        alert("Todos los campos son obligatorios.");
    }
}
function eliminarUsuario(id) {
    if (confirm("¿Está seguro de que desea eliminar este usuario?")) {
        fetch(`/eliminarUsuario/${id}`, {
            method: 'GET'
        })
            .then(response => {
                if (response.ok) {
                    alert("Usuario eliminado exitosamente.");
                    location.reload(); // Recarga la página para actualizar la tabla
                } else {
                    alert("Error al eliminar el usuario.");
                }
            })
            .catch(error => console.error("Error:", error));
    }
}
function editarUsuario(id) {
    const nombre = prompt("Ingrese el nuevo nombre del usuario:");
    const correo = prompt("Ingrese el nuevo correo del usuario:");
    const numeroTelefono = prompt("Ingrese el nuevo número de teléfono del usuario:");

    if (nombre && correo && numeroTelefono) {
        fetch(`/editarUsuario`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                id: id,
                nombre: nombre,
                correo: correo,
                numeroTelefono: numeroTelefono
            })
        })
            .then(response => {
                if (response.ok) {
                    alert("Usuario editado exitosamente.");
                    location.reload(); // Recarga la página para actualizar la tabla
                } else {
                    alert("Error al editar el usuario.");
                }
            })
            .catch(error => console.error("Error:", error));
    } else {
        alert("Todos los campos son obligatorios.");
    }
}

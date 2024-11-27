function mostrarTabla(tabla) {
    // Ocultar solo las tablas, no los botones
    document.getElementById("tabla-usuarios").style.display = "none";
    document.getElementById("tabla-pedidos").style.display = "none";

    if (tabla === 'usuarios') {
        document.getElementById("tabla-usuarios").style.display = "block";
    } else if (tabla === 'pedidos') {
        document.getElementById("tabla-pedidos").style.display = "block";
    }
}

function mostrarPopup(accion, idUsuario) {
    var modal = document.getElementById("modal-popup");
    var title = document.getElementById("popup-title");
    var form = document.getElementById("popup-form");
    var nombre = document.getElementById("nombre");
    var correo = document.getElementById("correo");
    var pass = document.getElementById("pass");
    var numerotel = document.getElementById("numerotel");

    if (accion === 'crear') {
        title.innerText = "Crear Usuario";
        form.action = "/crear";
        nombre.value = "";
        correo.value = "";
        pass.value = "";
        numerotel.value = "";
    } else if (accion === 'editar') {
        title.innerText = "Editar Usuario";
        form.action = "/editar/" + idUsuario; // Usar el idUsuario para editar el usuario correcto
        cargarDatosUsuario(idUsuario);
    }

    modal.style.display = "block";
}


// Función para cargar los datos de un usuario
function cargarDatosUsuario(idUsuario) {
    // Hacer una solicitud AJAX para obtener los datos del usuario por su ID
    fetch('/usuario/' + idUsuario)
        .then(response => response.json())
        .then(data => {
            // Rellenar los campos del formulario con los datos del usuario
            document.getElementById("nombre").value = data.nombre;
            document.getElementById("correo").value = data.correo;
            document.getElementById("pass").value = '';  // Dejar la contraseña vacía por seguridad
            document.getElementById("numerotel").value = data.numeroTelefono;
        })
        .catch(error => console.log('Error al cargar los datos del usuario:', error));
}

// Función para cerrar el popup
function cerrarPopup() {
    document.getElementById("modal-popup").style.display = "none";
}



// Función de eliminación para usuarios
function eliminarUsuario(id) {
    if (confirm("¿Seguro que deseas eliminar este usuario?")) {
        // Realizamos una petición DELETE al backend
        fetch('/usuario/' + id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    alert('Usuario eliminado con éxito');
                    // Aquí puedes agregar código para actualizar la lista de usuarios en la página, si es necesario
                    location.reload();  // Recarga la página para reflejar los cambios
                } else {
                    alert('Error al eliminar el usuario');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ocurrió un error al intentar eliminar al usuario');
            });
    }
}

// Función de eliminación para pedidos
function eliminarPedido(id) {
    fetch('/pedido/' + id, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.ok) {
                console.log('Pedido eliminado');
                // Aquí puedes hacer algo para actualizar la vista después de eliminar el pedido, como recargar la página o eliminar la fila de la tabla.
            } else {
                console.error('Error al eliminar el pedido');
            }
        })
        .catch(error => {
            console.error('Error en la solicitud:', error);
        });
}

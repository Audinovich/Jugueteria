function agregarMascota() {
    // Obtienen los valores del formulario
    var nombre = document.getElementById('nombre').value;
    var especie = document.getElementById('especie').value;
    var sexo = document.getElementById('sexo').value;
    var edad = document.getElementById('edad').value;
    var raza = document.getElementById('raza').value;
    var color = document.getElementById('color').value;

    var usuarioId = document.getElementById('usuario_id').value;

    if (usuarioId) {  // Verifica si el elemento tiene un valor
        console.log(usuarioId);
    } else {
        console.log('El elemento usuario_id no tiene un valor');
    }

    // Crea un objeto mascota con los valores del formulario
    var mascota = {
        nombre: nombre,
        especie: especie,
        sexo: sexo,
        edad: edad,
        raza: raza,
        color: color,
        usuario: {
            id: usuarioId
        }
    };

    // Hace una solicitud POST a la API para guardar la mascota
    fetch('http://localhost:8080/Mascota/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(mascota)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Mascota guardada con éxito:', data);
    })
    .catch((error) => {
        console.error('Error:', error);
    });

    alert('Agregar Mascota');
}

function consultarMascotas() {
    var usuarioId = document.getElementById('usuario_id').value;

    // Hace una solicitud GET a la API para obtener las mascotas del cliente
    fetch('http://localhost:8080/Mascota/findAll/' + usuarioId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => response.json())
    .then(data => {
        // Verificar si ya existe una tabla y eliminarla si es necesario
        var tablaExistente = document.getElementById('tablaMascotas');
        if (tablaExistente) {
            tablaExistente.remove();
        }

        // Crear una nueva tabla
        var tabla = document.createElement('table');
        tabla.id = 'tablaMascotas';

        // Crear la fila de encabezado
        var encabezado = tabla.insertRow();
        encabezado.insertCell().textContent = 'ID';
        encabezado.insertCell().textContent = 'Nombre';
        encabezado.insertCell().textContent = 'Especie';
        encabezado.insertCell().textContent = 'Sexo';
        encabezado.insertCell().textContent = 'Edad';
        encabezado.insertCell().textContent = 'Raza';
        encabezado.insertCell().textContent = 'Color';
        encabezado.insertCell().textContent = 'Acciones';

        // Añadir cada mascota a la tabla
        data.forEach(mascota => {
            var fila = tabla.insertRow();
            fila.insertCell().textContent = mascota.id;
            fila.insertCell().textContent = mascota.nombre;
            fila.insertCell().textContent = mascota.especie;
            fila.insertCell().textContent = mascota.sexo;
            fila.insertCell().textContent = mascota.edad;
            fila.insertCell().textContent = mascota.raza;
            fila.insertCell().textContent = mascota.color;

            // Crear botones de modificar y eliminar
            var acciones = fila.insertCell();
            var btnModificar = document.createElement('button');
            btnModificar.textContent = 'Modificar';
            btnModificar.onclick = function() {
                modificarMascota(mascota);
            };
            acciones.appendChild(btnModificar);

            var btnEliminar = document.createElement('button');
            btnEliminar.textContent = 'Eliminar';
            btnEliminar.onclick = function() {
                eliminarMascota(mascota.id);
            };
            acciones.appendChild(btnEliminar);
        });

        // Añadir la tabla al cuerpo del documento
        document.body.appendChild(tabla);
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function modificarMascota(mascota) {
    document.getElementById('editarNombre').value = mascota.nombre;
    document.getElementById('editarEspecie').value = mascota.especie;
    document.getElementById('editarSexo').value = mascota.sexo;
    document.getElementById('editarEdad').value = mascota.edad;
    document.getElementById('editarRaza').value = mascota.raza;
    document.getElementById('editarColor').value = mascota.color;

    // Mostrar el formulario de edición
    document.getElementById('formEditarMascota').style.display = 'block';

    // Guardar el ID de la mascota en un atributo del formulario
    document.getElementById('formEditarMascota').setAttribute('data-id', mascota.id);
}

function guardarCambios() {
    // Obtener el ID de la mascota desde el atributo data-id del formulario
    var id = document.getElementById('formEditarMascota').getAttribute('data-id');
    var usuarioId = document.getElementById('usuario_id').value;

    // Obtener los valores del formulario de edición
    var nombre = document.getElementById('editarNombre').value;
    var especie = document.getElementById('editarEspecie').value;
    var sexo = document.getElementById('editarSexo').value;
    var edad = document.getElementById('editarEdad').value;
    var raza = document.getElementById('editarRaza').value;
    var color = document.getElementById('editarColor').value;

    // Crear un objeto mascota con los valores del formulario
    var mascota = {
        nombre: nombre,
        especie: especie,
        sexo: sexo,
        edad: edad,
        raza: raza,
        color: color,
        usuario: {
            id: usuarioId
        }
    };

    // Realizar la solicitud PUT a la API con el ID de la mascota en la URL
    fetch('http://localhost:8080/Mascota/edith/' + id + '?usuarioId=' + usuarioId, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(mascota)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error en la solicitud: ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        console.log('Mascota modificada con éxito:', data);
        alert('Mascota modificada con éxito');
        // Ocultar el formulario de edición
        document.getElementById('formEditarMascota').style.display = 'none';
        // Actualizar la lista de mascotas
        consultarMascotas();
    })
    .catch((error) => {
        console.error('Error:', error);
        alert('Hubo un error al modificar la mascota: ' + error.message);
    });
}

function eliminarMascota(id) {
    var confirmacion = confirm('¿Estás seguro de que deseas eliminar esta mascota?');

    if (confirmacion) {
        fetch('http://localhost:8080/Mascota/delete/' + id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud: ' + response.statusText);
            }
            return response.text(); // Cambiar a text() para manejar respuestas no JSON
        })
        .then(data => {
            try {
                var jsonData = JSON.parse(data); // Intentar parsear como JSON
                console.log('Mascota eliminada con éxito:', jsonData);
            } catch (e) {
                console.log('Mascota eliminada con éxito:', data);
                alert('Mascota eliminada con éxito');
            }
            consultarMascotas();
        })
        .catch((error) => {
            alert('Hubo un error al eliminar la mascota: ' + error.message);
        });
    }
}

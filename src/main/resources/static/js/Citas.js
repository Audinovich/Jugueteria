
document.addEventListener('DOMContentLoaded', function() {
    consultarMascotas();
});

function consultarMascotas() {
    var usuarioId = document.getElementById('usuario_id').value;
    console.log('Valor de usuario_id:', usuarioId);

    fetch('http://localhost:8080/Mascota/findAll/' + usuarioId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => {
        console.log('Respuesta del servidor:', response);
        return response.json();
    })
    .then(data => {
        console.log('Datos recibidos:', data);
        var tablaExistente = document.getElementById('tablaMascotas');
        if (tablaExistente) {
            tablaExistente.innerHTML = '';
        }

        var tabla = document.createElement('table');
        tabla.id = 'tablaMascotas';

        var encabezado = tabla.insertRow();
        encabezado.insertCell().textContent = 'ID';
        encabezado.insertCell().textContent = 'Nombre';
        encabezado.insertCell().textContent = 'Acciones';
        encabezado.insertCell().textContent = 'Citas';

        data.forEach(mascota => {
            var fila = tabla.insertRow();
            fila.insertCell().textContent = mascota.id;
            fila.insertCell().textContent = mascota.nombre;

            var acciones = fila.insertCell();
            var btnSolicitarCita = document.createElement('button');
            btnSolicitarCita.textContent = 'Solicitar Cita';
            btnSolicitarCita.onclick = function() {
                mostrarCalendario(mascota.id, 'solicitar');
            };
            acciones.appendChild(btnSolicitarCita);

            var btnModificarCita = document.createElement('button');
            btnModificarCita.textContent = 'Modificar Cita';
            btnModificarCita.onclick = function() {
                mostrarCalendario(mascota.id, 'modificar');
            };
            acciones.appendChild(btnModificarCita);

            var btnEliminarCita = document.createElement('button');
            btnEliminarCita.textContent = 'Eliminar Cita';
            btnEliminarCita.onclick = function() {
                eliminarCita(mascota.id);
            };
            acciones.appendChild(btnEliminarCita);

            var celdaCitas = fila.insertCell();
            celdaCitas.id = 'citasMascota_' + mascota.id;

            // Consultar y mostrar citas automáticamente
            consultarCitas(mascota.id, fila);
        });

        document.getElementById('tablaMascotas').appendChild(tabla);
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function mostrarCalendario(mascotaId, accion) {
    // Aquí puedes mostrar un calendario para seleccionar la fecha y hora de la cita
    // Una vez seleccionada la fecha y hora, puedes hacer la solicitud a la API

    var cita = {
        mascotaId: mascotaId,
        fecha: '2024-08-08T10:00:00', // Ejemplo de fecha y hora seleccionada
        accion: accion
    };

    if (accion === 'solicitar') {
        fetch('http://localhost:8080/Citas/save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(cita)
        })
        .then(response => response.json())
        .then(data => {
            console.log('Cita solicitada:', data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    } else if (accion === 'modificar') {
        fetch('http://localhost:8080/Citas/edit/' + mascotaId, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(cita)
        })
        .then(response => response.json())
        .then(data => {
            console.log('Cita modificada:', data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    }
}

function eliminarCita(mascotaId) {
    fetch('http://localhost:8080/Citas/delete/' + mascotaId, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => response.text())
    .then(data => {
        console.log('Cita eliminada:', data);
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function consultarCitas(mascotaId, fila) {
    fetch('http://localhost:8080/Citas/find/' + mascotaId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => response.json())
    .then(data => {
        console.log('Citas recibidas:', data);
        var celdaCitas = fila.querySelector('#citasMascota_' + mascotaId);
        celdaCitas.innerHTML = '';

        data.forEach(cita => {
            var citaInfo = document.createElement('div');
            citaInfo.textContent = 'Fecha: ' + cita.fecha + ', Estado: ' + cita.estado;
            celdaCitas.appendChild(citaInfo);
        });
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}
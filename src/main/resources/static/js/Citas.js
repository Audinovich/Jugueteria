
document.addEventListener('DOMContentLoaded', function() {
    consultarMascotas();
});

function consultarMascotas() {
    var usuarioId = document.getElementById('usuario_id').value;
    var citaId =  document.getElementById('cita_id').value;
    console.log('Valor de usuario_id:', usuarioId);
    console.log('Valor de la citaID:', citaId);

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
    var calendarioDiv = document.createElement('div');
    calendarioDiv.id = 'calendarioDiv';
    document.body.appendChild(calendarioDiv);

    var inputFecha = document.createElement('input');
    inputFecha.type = 'text';
    inputFecha.id = 'fechaCita';
    calendarioDiv.appendChild(inputFecha);

    var picker = new Pikaday({
        field: document.getElementById('fechaCita'),
        format: 'YYYY-MM-DD',
        onSelect: function(date) {
            var fechaSeleccionada = moment(date).format('YYYY-MM-DD');
            console.log(fechaSeleccionada);
        }
    });

    var selectHora = document.createElement('select');
    selectHora.id = 'horaCita';
    calendarioDiv.appendChild(selectHora);

    var horasDisponibles = generarHorasDisponibles();
    horasDisponibles.forEach(hora => {
        var option = document.createElement('option');
        option.value = hora;
        option.textContent = hora;
        selectHora.appendChild(option);
    });

    var btnConfirmar = document.createElement('button');
    btnConfirmar.textContent = 'Confirmar Cita';
    btnConfirmar.onclick = function() {
        var fechaSeleccionada = moment(inputFecha.value).format('YYYY-MM-DD');
        var fechaHora = fechaSeleccionada + 'T' + selectHora.value + ':00';
        var cita = {
            mascota: {
                id: mascotaId
            },
            fechaHora: fechaHora
        };

        var citaModificada = {
            cita:{
            id : citaId
            },
        fechaHora: fechaHora

        };

        console.log('el json de la cita es: ', cita);

        if (accion === 'solicitar') {
            fetch('http://localhost:8080/Citas/save', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(cita)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                console.log('Cita solicitada:', data);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
        } else if (accion === 'modificar') {
            fetch('http://localhost:8080/Citas/edit/' + 14, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(citaModificada)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                console.log('Cita modificada:', data);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
        }
    };
    calendarioDiv.appendChild(btnConfirmar);
}

function generarHorasDisponibles() {
    var horas = [];
    var horaInicio = 8.5; // 8:30
    var horaFin = 19; // 19:00

    for (var i = horaInicio; i < horaFin; i += 0.5) {
        var hora = Math.floor(i);
        var minutos = (i % 1) * 60;
        var horaFormateada = ('0' + hora).slice(-2) + ':' + ('0' + minutos).slice(-2);
        horas.push(horaFormateada);
    }

    return horas;
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
    console.log('el id de la mascota es', mascotaId);
    fetch('http://localhost:8080/Citas/findByMascota/' + mascotaId, {
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
            citaInfo.textContent = 'Fecha: ' + cita.fechaHora + ', Estado: ' + (cita.estado || 'Desconocido');
            celdaCitas.appendChild(citaInfo);
        });
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}
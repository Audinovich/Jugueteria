document.addEventListener('DOMContentLoaded', function() {
    consultarMascotas();
});
var practicaSeleccionadaId;
var selectPractica = document.getElementById('selectPractica');

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
                mostrarPracticas(mascota.id); // Llamar a mostrarPracticas cuando se haga clic en "Solicitar Cita"
            };
            acciones.appendChild(btnSolicitarCita);



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

function mostrarPracticas(mascotaId) {
    fetch('http://localhost:8080/Practica/all', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => {
        console.log('Respuesta del servidor DE PRACTICAS:', response);
        return response.json();
    })
    .then(data => {
        console.log('Datos recibidos DESDE PRACTICAS:', data); // Aquí puedes ver los datos que trae el fetch
        var tablaExistente = document.getElementById('tablaPracticas');
        if (tablaExistente) {
            tablaExistente.innerHTML = '';
        }

        // Crear el menú desplegable de prácticas
        var selectPractica = document.createElement('select');
        selectPractica.id = 'selectPractica';
        selectPractica.style.width = '300px'; // Ajustar el ancho del menú desplegable
        document.body.appendChild(selectPractica);

        // Llenar el menú desplegable con las prácticas
        data.forEach(practica => {
            var option = document.createElement('option');
            option.value = practica.id;
            var nombre = practica.nombre || 'Nombre no disponible';
            var descripcion = practica.descripcion || 'Descripción no disponible';
            option.textContent = `${descripcion}`; // Mostrar nombre y descripción
            selectPractica.appendChild(option);
        });

        // Agregar un evento para manejar la selección de una práctica
        selectPractica.addEventListener('change', function() {
            var practicaSeleccionada = selectPractica.value;
            console.log('Práctica seleccionada:', practicaSeleccionada);

            // Mostrar el calendario para seleccionar la fecha y hora
            mostrarCalendario(mascotaId, 'solicitar');
        });
    })
    .catch(error => {
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
    inputFecha.value = '';

    var debounceTimeout;
    var picker = new Pikaday({
        field: inputFecha, // Asegúrate de que el campo esté correctamente referenciado
        format: 'YYYY-MM-DD',
        onSelect: function(date) {
            clearTimeout(debounceTimeout);
            debounceTimeout = setTimeout(function() {
                var fechaSeleccionada = moment(date).format('YYYY-MM-DD');
                console.log(fechaSeleccionada);
            }, 300); // Ajusta el retraso del debounce según sea necesario
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

        if (!practicaSeleccionadaId) {
            console.error('Error: No se ha seleccionado una práctica.');
            return;
        }

        var cita = {
            mascota: {
                id: mascotaId
            },
            fechaHora: fechaHora,
            practica: {
                id: practicaSeleccionada
            }
        };

        console.log('El JSON de la cita es: ', cita);

        if (accion === 'solicitar') {
            fetch(`http://localhost:8080/Citas/save?idPractica=${practicaSeleccionada}`, {
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
//ELIMINA TODAS LAS CITAS CON EL ID DE LA MASCOTA
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
//CONSULTA TODAS LAS CITAS POR ID DE MASCOTA
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
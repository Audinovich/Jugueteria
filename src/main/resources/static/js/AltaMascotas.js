


function agregarMascota() {
    // Obtienen los valores del formulario
    var nombre = document.getElementById('nombre').value;
    var especie = document.getElementById('especie').value;
    var sexo = document.getElementById('sexo').value;
    var edad = document.getElementById('edad').value;
    var raza = document.getElementById('raza').value;
    var color = document.getElementById('color').value;

    var usuario_id = document.getElementById('usuario_id').value;



      if (usuario_id) {  // Verifica si el elemento tiene un valor
            console.log(usuario_id);
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
        usuario:{id:usuario_id }
    };

    //  hace una solicitud POST a la API para guardar la mascota
    fetch('http://localhost:8080/Mascota/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        //transforma a JSON
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


    var usuario_id = document.getElementById('usuario_id').value;
    console.log(usuario_id);
    // Hace una solicitud GET a la API para obtener las mascotas del cliente
    fetch('http://localhost:8080/Mascota/findAll/' + usuario_id,
    {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }

        }
    )
    .then(response => response.json())
    .then(data => {

    // Crear una tabla
    var tabla = document.createElement('table');

    // Crear la fila de encabezado
    var encabezado = tabla.insertRow();
    encabezado.insertCell().textContent = 'ID';
    encabezado.insertCell().textContent = 'Nombre';
    encabezado.insertCell().textContent = 'Especie';
    encabezado.insertCell().textContent = 'Sexo';
    encabezado.insertCell().textContent = 'Edad';
    encabezado.insertCell().textContent = 'Raza';
    encabezado.insertCell().textContent = 'Color';

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
    });

    // Añadir la tabla al cuerpo del documento
    document.body.appendChild(tabla);
})
.catch((error) => {
    console.error('Error:', error);
});

/*
        var listaMascotas = document.getElementById('listaMascotas');
         console.log(listaMascotas);
        // Limpia la lista de mascotas
        listaMascotas.innerHTML = '';

        // Añade cada mascota a la lista
        data.forEach(mascota => {
            var mascotaDiv = document.createElement('div');
            mascotaDiv.textContent = 'Nombre: ' + mascota.nombre + ', Especie: ' + mascota.especie + ', Edad: ' + mascota.edad;
            listaMascotas.appendChild(mascotaDiv);
        });
    })
    .catch((error) => {
        console.error('Error:', error);
    });

    */
}


document.addEventListener('DOMContentLoaded', (event) => {
    document.querySelector('form').addEventListener('submit', function(event) {
        event.preventDefault();
        agregarMascota();
        consultarMascotas();
    });


});

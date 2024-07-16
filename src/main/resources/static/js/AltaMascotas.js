

    function agregarMascota() {
        // Obtienen los valores del formulario
        var nombre = document.getElementById('nombre').value;
        var especie = document.getElementById('especie').value;
        var sexo = document.getElementById('sexo').value;
        var edad = document.getElementById('edad').value;
        var raza = document.getElementById('raza').value;
        var color = document.getElementById('color').value;
    
        // Crea un objeto mascota con los valores del formulario
        var mascota = {
            nombre: nombre,
            especie: especie,
            sexo: sexo,
            edad: edad,
            raza: raza,
            color: color
        };
    
        var usuario_id = 'usuario_id';
        //  hace una solicitud POST a la API para guardar la mascota
        fetch('http://localhost:8080/Mascota/save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                m: mascota,
                usuario_id: usuario_id
            }),
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
    
    document.querySelector('form').addEventListener('submit', function(event) {
        event.preventDefault();
        agregarMascota();
    });

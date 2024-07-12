
//

document.querySelector('form').addEventListener('submit', function(event) {
    event.preventDefault();

    //SE CAPTURAN LOS VALORES DEL FORMULARIO
    var Usuario = {
        name: document.getElementById('name').value,
        password: document.getElementById('password').value,
        email: document.getElementById('email').value,
        address: document.getElementById('address').value,
        phone: document.getElementById('phone').value
    };

    fetch('http://localhost:8080/Usuario/save', {
        method: 'POST',
        headers: {
        //indicar que el cuerpo de la solicitud contiene JSON.
            'Content-Type': 'application/json'
        },
        //SE TRANSFORMA USUARIO A JSON
        body: JSON.stringify(Usuario)
    })
    .then(response => response.json())
    .then(data => console.log(data))

    .catch((error) => {
        console.error('Error:', error);

    });
});
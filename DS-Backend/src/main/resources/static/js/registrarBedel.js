const boton = document.getElementById('aceptarBtn');

let registrarBedel = async () => {
    limpiarMensajeError();

    let bedel = {
        nombre: document.getElementById("nombre").value,
        apellido: document.getElementById("apellido").value,
        usuario: document.getElementById("usuario").value.trim(),
        contrasenia: document.getElementById("contrasenia").value,
        confContrasenia: document.getElementById("confContrasenia").value,
        email: document.getElementById("email").value,
        turno: parseInt(document.getElementById("turno").value.replace(/\D/g, ''))
    };

    // Validaciones de campos, muestra un error en el campo mensaje error dentro del html, deberiamos agregar un log interno¿?

    if (!bedel.nombre || !bedel.apellido || !bedel.usuario || !bedel.contrasenia || !bedel.confContrasenia || !bedel.email || !bedel.turno) {
        mensajeError.textContent = "Error: todos los campos son obligatorios.";
        return;
    }

    if (bedel.contrasenia !== bedel.confContrasenia) {
        mensajeError.textContent = "Error: las contraseñas no coinciden.";
        return;
    }

    if (bedel.usuario.length < 5 || bedel.usuario.length > 30) {
        mensajeError.textContent = "Error: El usuario debe tener entre 5 y 30 caracteres.";
        return;
    }

    if (bedel.nombre.length > 40) {
        mensajeError.textContent = "Error: El nombre debe tener menos de 40 caracteres.";
        return;
    }

    if (bedel.apellido.length > 40) {
        mensajeError.textContent = "Error: El apellido debe tener menos de 40 caracteres.";
        return;
    }

    // es nesesario agregar la verificacion de la contraseña aca o solo se verifica si son iguales y se hace la verificacion en service¿?

    try {
        limpiarMensajeError();
        const peticion = await fetch("http://localhost:4400/bedel/registrar", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(bedel)
        });

        // Manejo de respuestas
        if (peticion.status === 409) {
            // 409 CONFLICTO
            const errorData = await peticion.json();
            boton.style.backgroundColor = 'red';
            mostrarResultadoModal(errorData.mensaje);
        } else if (peticion.status === 400) {
            // 400 BAD REQUEST
            const errorData = await peticion.json();
            boton.style.backgroundColor = 'red';
            mostrarResultadoModal(errorData.mensaje);
        } else if (peticion.status === 200 || peticion.status === 201) {
            // 200 o 201 ÉXITO
            const respuesta = await peticion.json();
            boton.style.backgroundColor = 'green';
            mostrarResultadoModal("Bedel registrado correctamente");
            resetearFormulario();
        } else if (peticion.status === 500) {
            // 500 INTERNAL SERVER ERROR
            boton.style.backgroundColor = 'red';
            mostrarResultadoModal("Error en el servidor. Inténtalo nuevamente más tarde.");
        } else {
            mostrarResultadoModal("Error inesperado: " + peticion.status);
        }
        
    } catch (error) {
        console.log("Error en la petición: " + error.message);
    }
    
};

const resetearFormulario = () => {
    document.getElementById("nombre").value = '';
    document.getElementById("apellido").value = '';
    document.getElementById("usuario").value = '';
    document.getElementById("contrasenia").value = '';
    document.getElementById("confContrasenia").value = '';
    document.getElementById("email").value = '';
    document.getElementById("turno").selectedIndex = 0;
};

const limpiarMensajeError = () => {
    mensajeError.textContent = "";
}

// Función para mostrar el modal
const mostrarResultadoModal = (mensaje) => {
    document.getElementById("resultadoMensaje").textContent = mensaje;
    document.getElementById("resultadoModal").style.display = "block";
};

// Cerrar el modal
document.getElementById("cerrarModal").onclick = function() {
    document.getElementById("resultadoModal").style.display = "none";
};

document.getElementById("aceptarBtn").onclick = function() {
    document.getElementById("resultadoModal").style.display = "none";
};
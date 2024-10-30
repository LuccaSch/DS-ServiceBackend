let registrarBedel = async () => {
    let bedel = {
        nombre: document.getElementById("nombre").value,
        apellido: document.getElementById("apellido").value,
        usuario: document.getElementById("usuario").value.trim(),
        pass: document.getElementById("pass").value,
        cpass: document.getElementById("conf_pass").value,
        email: document.getElementById("email").value,
        turno: parseInt(document.getElementById("turno").value.replace(/\D/g, ''))
    };

    // Validaciones de campos, muestra un error en el campo mensaje error dentro del html, deberiamos agregar un log interno¿?

    if (!bedel.nombre || !bedel.apellido || !bedel.usuario || !bedel.pass || !bedel.cpass || !bedel.email || !bedel.turno) {
        mensajeError.textContent = "Error: todos los campos son obligatorios.";
        return;
    }

    if (bedel.pass !== bedel.cpass) {
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
        const peticion = await fetch("http://localhost:4400/bedel/registrar", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(bedel)
        });

        if (peticion.status === 400) {
            const errorData = await peticion.json();
            console.error("Error: " + errorData.message); 
            mensajeError.textContent = errorData.message; 
            return; 
        }
        else {
            const respuesta = await peticion.json();
            console.log("Bedel registrado:", respuesta);
            resetearFormulario();
        }
        
    } catch (error) {
        console.error("Error en la petición:", error);
        mensajeError.textContent = "Error en la petición: " + error.message;
    }
};

const resetearFormulario = () => {
    document.getElementById("nombre").value = '';
    document.getElementById("apellido").value = '';
    document.getElementById("usuario").value = '';
    document.getElementById("pass").value = '';
    document.getElementById("conf_pass").value = '';
    document.getElementById("email").value = '';
    document.getElementById("turno").selectedIndex = 0;
};
//CONSTANTES GLOBALES

//boton buscar
const boton = document.getElementById('buscar');

//mensaje de error de parametros de busqueda
const mensajeError = document.getElementById("mensajeError");

//Modal de resultados
const modalResultado = document.getElementById("resultadoModal");
const mensajeModalResultado = document.getElementById("resultadoMensaje");
const botonAceptarResultado = document.getElementById("aceptarBtn");

// ----------------------------BUSQUEDA DE BEDELES----------------------------

boton.addEventListener("click", function () {

    //Creamos las variables para el conteiner y el filtro para la busqueda
    const bedelListContainer = document.getElementById("bedel-list");
    const valorBusqueda = document.getElementById("valor_busqueda").value;
    const filtroSeleccionado = document.getElementById("filtro").value;

    limpiarMensajeError();

    // Validamos que si se haya seleccionado un filtro, que el valor de búsqueda no esté vacío
    if (filtroSeleccionado !== "0" && !valorBusqueda) {
        mostrarMensajeError("Por favor, Escriba un valor de búsqueda.");
        return;
    }

    // Limpiar contenedor antes de cargar
    bedelListContainer.innerHTML = "";

    //creamos el filtro a enviar a la API rest
    const filtroDatos = {
        filtro: filtroSeleccionado,
        valorBusqueda: valorBusqueda
    };

    // Comienzo de la petición
    try {
        limpiarMensajeError();

        fetch("/admin/api/getBedel", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(filtroDatos)
        })
            .then(response => {
                //Si la peticion es erronea (Status!=200) se mostrara el modal De resultado con el Mensaje
                if (!response.ok) {
                    return response.json().then(data => {
                        mostrarModalConMensajeError(data.mensaje || "Ocurrio un error desconocido, por favor contactar con soporte."); 
                    });
                }
                //si no hay errores se retorna la lista de bedeles
                return response.json();
            })
            .then(data => {

                // Si no hay coincidencias para el filtro seleccionado, informamos y cortamos la ejecucion
                if (data.length === 0) {
                    bedelListContainer.textContent = "No se encontraron bedeles.";
                    return;
                }

                //Iteramos todos los bedeles para crear los ItemBedel
                data.forEach(bedel => {
                    // Creamos el contenedor para cada bedel
                    const bedelItem = document.createElement("div");
                    bedelItem.className = "bedel-item";

                    //Consultamos si el bedel esta activo o inactivo para setear el color del fondo
                    bedelItem.style.backgroundColor = bedel.estado ? "lightgreen" : "yellow";

                    //Cargamos la informacion de cada bedel (Se puede mejorar cambiando los |)
                    const bedelInfo = document.createElement("span");
                    bedelInfo.textContent = `"ID": ${bedel.id} | "Usuario": ${bedel.usuario} | "Nombre": ${bedel.nombre} | "Apellido": ${bedel.apellido} | "Turno": ${bedel.turno}`;
                    bedelItem.appendChild(bedelInfo);

                    //Se agrega el Botón para modificar: se setean los atributos y luego el boton que llamara a openModalModificar
                    const modificarBtn = document.createElement("button");
                    modificarBtn.textContent = "Modificar";
                    modificarBtn.className = "modificar-btn";
                    modificarBtn.addEventListener("click", () => {
                        openModalModificar(bedel);
                    });
                    bedelItem.appendChild(modificarBtn);

                    //Se agrega el Botón para eliminar/activar: se setean los atributos y luego el boton que llamara a openModalEliminar
                    const eliminarBtn = document.createElement("button");
                    eliminarBtn.textContent = bedel.estado ? "Eliminar" : "Activar";
                    eliminarBtn.className = "eliminar-btn";
                    eliminarBtn.style.backgroundColor = bedel.estado ? "red" : "green";
                    eliminarBtn.addEventListener("click", () => {
                        openModalEliminar(bedel, eliminarBtn, bedelItem);
                    });
                    bedelItem.appendChild(eliminarBtn);

                    bedelListContainer.appendChild(bedelItem);
                });
            })
            .catch(error => {
                console.error("Error:", error);
                mostrarModalConMensajeError("No se pudo cargar la lista de bedeles por un error interno.")
            });
    } catch (error) {
        console.log("Error en la petición: " + error.message);
        mostrarModalConMensajeError("Error al realizar la petición, por favor intente más tarde.")
    }
});

// ----------------------------ELIMINACION DE BEDELS----------------------------

function openModalEliminar(bedel, button, bedelItem) {

    // Mostrar el modal de eliminar bedel
    const modalEliminar = document.getElementById('modal-eliminar');
    const usuarioEliminar = document.getElementById('bedel-eliminar-usuario');
    const nombreEliminar = document.getElementById('bedel-eliminar-nombre');
    const apellidoEliminar = document.getElementById('bedel-eliminar-apellido');
    const turnoEliminar = document.getElementById('bedel-eliminar-turno');

    // Rellenar la información del bedel en el modal
    usuarioEliminar.textContent = bedel.usuario;
    nombreEliminar.textContent = bedel.nombre;
    apellidoEliminar.textContent = bedel.apellido;
    turnoEliminar.textContent = bedel.turno;

    // Mostrar el modal
    modalEliminar.style.display = "block";

    // Al hacer clic en el botón "Guardar" se llama a toggleEstadoBedel
    const saveButton = document.getElementById('save-delete-button');
    saveButton.onclick = function() {
        toggleEstadoBedel(bedel, button, bedelItem);
        // Cerrar el modal después de realizar la acción
        modalEliminar.style.display = "none"; 
    };

    // Al hacer clic en "Cancelar" se cierra el modal sin hacer nada
    const cancelButton = document.getElementById('cancel-delete-button');
    cancelButton.onclick = function() {
        modalEliminar.style.display = "none"; // Cerrar el modal sin hacer nada
    };

    const closeButton = document.getElementById('close-button-eliminar');
    closeButton.onclick = function() {
        modalEliminar.style.display = "none"; // Cerrar el modal sin hacer nada
    };
}

// TOGGLE ESTADO (Activar/Eliminar)
function toggleEstadoBedel(bedel, button, bedelItem) {
    //Dependiendo del estado del bedel se llamara a deleteBedel o activarBedel
    const url = bedel.estado 
        ? `/admin/api/deleteBedel/${bedel.id}` 
        : `/admin/api/activarBedel/${bedel.id}`;
    const nuevoEstado = !bedel.estado; //Se invierte el estado original

    //comienza la peticion
    fetch(url, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            //Si hay error muestra mostrarModalConMensajeError y el mensaje
            if (!response.ok) {
                return response.json().then(data => {
                    mostrarModalConMensajeError(data.mensaje || "Ocurrio un error desconocido, por favor contactar con soporte."); 
                });
            }
            return response.json();
        })
        .then(() => {

            // Actualizamos el estado visual del Bedel
            bedel.estado = nuevoEstado;
            bedelItem.style.backgroundColor = bedel.estado ? "lightgreen" : "yellow";
            button.textContent = bedel.estado ? "Eliminar" : "Activar";
            button.style.backgroundColor = bedel.estado ? "red" : "green";
            
            mostrarModalConMensajeExito(`Bedel ${bedel.estado ? "activado" : "eliminado"} correctamente.`); 
        })
        .catch(error => {
            console.error("Error:", error);
            mostrarModalConMensajeError( "Ocurrio un error desconocido, por favor contactar con soporte.");
        });
}

// ----------------------------MODIFICAR DE BEDELS----------------------------

function openModalModificar(bedel) {
    const modal = document.getElementById("modal-modificar");
    document.getElementById("bedel-id").value = bedel.id;
    document.getElementById("bedel-modificar-usuario").value = bedel.usuario;

    document.getElementById("bedel-modificar-nombre").value = "";
    document.getElementById("bedel-modificar-apellido").value = "";
    document.getElementById("bedel-modificar-turno").value = "";
    document.getElementById("bedel-modificar-contrasenia").value = "";
    document.getElementById("bedel-modificar-confContrasenia").value = "";

    document.getElementById("bedel-modificar-nombre").placeholder = bedel.nombre;
    document.getElementById("bedel-modificar-apellido").placeholder = bedel.apellido;
    document.getElementById("bedel-modificar-turno").placeholder = bedel.turno;
    
    modal.style.display = "block";
}

function closeModalModificar() {
    document.getElementById("modal-modificar").style.display = "none";
}

document.getElementById("cancel-modificar-button").addEventListener("click", closeModalModificar);

document.getElementById("save-modificar-button").addEventListener("click", () => {
    let updatedBedel = {
        id : document.getElementById("bedel-id").value,
        usuario: document.getElementById("bedel-modificar-usuario").value,
        nombre: document.getElementById("bedel-modificar-nombre").value || null,
        apellido: document.getElementById("bedel-modificar-apellido").value || null,
        contrasenia : document.getElementById("bedel-modificar-contrasenia").value || null,
        confContrasenia : document.getElementById("bedel-modificar-confContrasenia").value || null,
        turno: document.getElementById("bedel-modificar-turno").value || null
    }

    //Verificaciones

    if (updatedBedel.contrasenia !== updatedBedel.confContrasenia) {
        mostrarModalConMensajeError("Error: las contraseñas no coinciden."); 
        return;
    }

    if (updatedBedel.nombre!== null && updatedBedel.nombre.length > 40) {
        mostrarModalConMensajeError("Error: El nombre debe tener menos de 40 caracteres."); 
        return;
    }

    if (updatedBedel.apellido!== null && updatedBedel.apellido.length > 40) {
        mostrarModalConMensajeError("Error: El apellido debe tener menos de 40 caracteres."); 
        return;
    }

    if(updatedBedel.turno!== null && (updatedBedel.turno!== "1" && updatedBedel.turno!== "2")){
        mostrarModalConMensajeError("Error: turno invalido, solo existen dos turnos 1 y 2."); 
        return;
    }


    //Comienza la peticion

    fetch(`/admin/api/updateBedel`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(updatedBedel)
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(data => {
                    mostrarModalConMensajeError(data.mensaje || "Ocurrio un error desconocido, por favor contactar con soporte."); 
                });
            }
            return response.json();
        })
        .then(() => {
            closeModalModificar();
            mostrarModalConMensajeExito("Bedel actualizado correctamente."); 
        })
        .catch(error => {
            console.error("Error:", error);
            mostrarModalConMensajeError("Ocurrió un error al actualizar el Bedel."); 
        });
});



// ----------------------------MENSAJES, MODALES Y FUNCIONES AUXILIARES----------------------------

function mostrarModalConMensajeError(mensaje) {

    botonAceptarResultado.style.backgroundColor = "red";

    // Configurar el mensaje
    mensajeModalResultado.textContent = mensaje;

    // Mostrar el modal
    modalResultado.style.display = "block";
}

function mostrarModalConMensajeExito(mensaje) {

    botonAceptarResultado.style.backgroundColor = "lightgreen";

    // Configurar el mensaje
    mensajeModalResultado.textContent = mensaje;

    // Mostrar el modal
    modalResultado.style.display = "block";
}

// Cerrar modal resultado al hacer clic en el botón 'Aceptar'
botonAceptarResultado.onclick = () => {
    modalResultado.style.display = "none";
};

// Cerrar modal al hacer clic en el botón de cerrar
document.getElementById("close-button-resultado").onclick = () => {
    modalResultado.style.display = "none";
};


const mostrarMensajeError = (mensaje) => {
    mensajeError.textContent = mensaje;
};

const limpiarMensajeError = () => {
    mensajeError.textContent = "";
};
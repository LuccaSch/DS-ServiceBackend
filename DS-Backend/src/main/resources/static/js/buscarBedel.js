const boton = document.getElementById('buscar');
const mensajeError = document.getElementById("mensajeError");

// BUSQUEDA DE BEDELS

boton.addEventListener("click", function () {
    const bedelListContainer = document.getElementById("bedel-list");
    const valorBusqueda = document.getElementById("valor_busqueda").value;
    const filtroSeleccionado = document.getElementById("filtro").value;

    limpiarMensajeError();

    // Validamos que se haya seleccionado un filtro y que el valor de búsqueda no esté vacío
    if (filtroSeleccionado !== "0" && !valorBusqueda) {
        mostrarMensajeError("Por favor, Escriba un valor de búsqueda.");
        return;
    }

    // Limpiar contenedor antes de cargar
    bedelListContainer.innerHTML = "";

    const filtroDatos = {
        filtro: filtroSeleccionado,
        valorBusqueda: valorBusqueda
    };

    // Comienzo de la petición
    try{
        limpiarMensajeError();

        fetch("/admin/api/getBedel", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(filtroDatos)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Ocurrio un error inesperado intente mas tarde.");
                }
                return response.json();
            })
            .then(data => {

                // Si el filtro no tiene ninguna coincidencia informamos y terminamos la ejecución

                if (data.length === 0) {
                    bedelListContainer.textContent = "No se encontraron bedels.";
                    return;
                }

                data.forEach(bedel => {
                    // Creamos el contenedor para cada bedel

                    const bedelItem = document.createElement("div");
                    bedelItem.className = "bedel-item";

                    // Según el estado le seteamos el color (verde:"ACTIVO", amarillo:"ELIMINADO")
                    bedelItem.style.backgroundColor = bedel.estado ? "lightgreen" : "yellow";

                    const bedelInfo = document.createElement("span");
                    bedelInfo.textContent = `"ID": ${bedel.id} | "Usuario": ${bedel.usuario} | "Nombre": ${bedel.nombre} | "Apellido": ${bedel.apellido} | "Turno": ${bedel.turno}`;
                    bedelItem.appendChild(bedelInfo);

                    //Consultamos el estado del bedel 

                    bedelItem.style.backgroundColor = bedel.estado ? "lightgreen" : "yellow";
                    // AGREGACIÓN DE BOTONES

                    // MODIFICAR
                    const modificarBtn = document.createElement("button");
                    modificarBtn.textContent = "Modificar";
                    modificarBtn.className = "modificar-btn";
                    modificarBtn.addEventListener("click", () => {
                        openModalModificar(bedel); 
                    });
                    bedelItem.appendChild(modificarBtn);

                    // ELIMINAR
                    const eliminarBtn = document.createElement("button");
                    eliminarBtn.textContent = bedel.estado ? "Eliminar" : " Activar ";
                    eliminarBtn.className = "eliminar-btn";
                    eliminarBtn.style.backgroundColor = bedel.estado ? "lightred" : "green";
                    eliminarBtn.addEventListener("click", () => {
                        openModalEliminar(bedel);
                    });
                    bedelItem.appendChild(eliminarBtn);

                    // Agregar el Bedel al contenedor de bedels
                    bedelListContainer.appendChild(bedelItem);
                });
            })
            .catch(error => {
                console.error("Error:", error);
                bedelListContainer.textContent = "No se pudo cargar la lista de bedels.";
            });
    }
    catch(error){
        console.log("Error en la petición: " + error.message);
        mensajeError.textContent = "Error al realizar la peticion, por favor intente mas tarde.";
    }
});

const mostrarMensajeError = (mensaje) => {
    mensajeError.textContent = mensaje;
}

const limpiarMensajeError = () => {
    mensajeError.textContent = "";
}

// MODIFICACION

//Funcion para abrir el Modal de modificacon
function openModalModificar(bedel) {
    const modal = document.getElementById("modal-modificar");

    document.getElementById("bedel-id").value = bedel.id;
    document.getElementById("bedel-modificar-usuario").value = bedel.usuario;
    document.getElementById("bedel-modificar-nombre").placeholder = bedel.nombre;
    document.getElementById("bedel-modificar-apellido").placeholder = bedel.apellido;
    document.getElementById("bedel-modificar-turno").placeholder = bedel.turno;

    modal.style.display = "block";
}

// Función para cerrar el modal de modificacon
function closeModalModificar() {
    const modal = document.getElementById("modal-modificar");
    modal.style.display = "none";
}

// Botones dentro del modal

//cancelar

document.getElementById("cancel-modificar-button").addEventListener("click", () => {
    closeModalModificar();
})

// Guardar modificacion
document.getElementById("save-modificar-button").addEventListener("click", () => {
    let updatedBedel = {
        id : document.getElementById("bedel-id").value,
        usuario: document.getElementById("bedel-modificar-usuario").value,
        nombre: document.getElementById("bedel-modificar-nombre").value || null,
        apellido: document.getElementById("bedel-modificar-apellido").value || null,
        turno: document.getElementById("bedel-modificar-turno").value || null
    }

    fetch(`/admin/api/updateBedel`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(updatedBedel)
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(errorData => {
                    throw new Error("No se pudo actualizar el Bedel: " + errorData.message); 
                });
            }
            return response.json();
        })
        .then(data => {
            alert("Bedel actualizado correctamente.");
            closeModalModificar();
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Ocurrió un error al intentar actualizar el Bedel.");
        });
})

document.querySelector("#close-button-modificar").addEventListener("click", closeModalModificar);


window.addEventListener("click", (event) => {
    const modal = document.getElementById("modal-modificar");
    if (event.target === modal) {
        closeModalModificar();
    }
});

// ELIMINACION

// modal de eliminación
function openModalEliminar(bedel) {
    const modal = document.getElementById("modal-eliminar");

    // Mostrar los datos del Bedel en el modal
    document.getElementById("bedel-eliminar-usuario").value = bedel.usuario;
    document.getElementById("bedel-eliminar-nombre").value = bedel.nombre;
    document.getElementById("bedel-eliminar-apellido").value = bedel.apellido;
    document.getElementById("bedel-eliminar-turno").value = bedel.turno;

    // Mostrar el modal
    modal.style.display = "block";
}

// Función para cerrar el modal
function closeModalEliminar() {
    const modal = document.getElementById("modal-eliminar");
    modal.style.display = "none";
}

document.getElementById("cancel-delete-button").addEventListener("click", () => {
    closeModalEliminar();
})

// Eliminar el Bedel
document.getElementById("save-delete-button").addEventListener("click", () => {
    bedelId = document.getElementById("bedel-id").value;

    fetch(`/admin/api/deleteBedel/${bedelId}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        }
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(errorData => {
                throw new Error("No se pudo eliminar el Bedel: " + errorData.message); 
            });
        }
        return response.json();
    })
    .then(data => {
        alert("Bedel eliminado correctamente.");
        closeModalEliminar(); // Cerrar el modal después de eliminar
    })
    .catch(error => {
        console.error("Error:", error);
        alert("Ocurrió un error al intentar eliminar el Bedel.");
    });
});

// Cerrar el modal al hacer clic en la X
document.querySelector("#close-button-eliminar").addEventListener("click", closeModalEliminar);

// Cerrar el modal si se hace clic fuera de él
window.addEventListener("click", (event) => {
    const modal = document.getElementById("modal-eliminar");
    if (event.target === modal) {
        closeModalEliminar();
    }
});
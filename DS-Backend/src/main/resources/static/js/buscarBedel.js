const boton = document.getElementById('buscar');
const mensajeError = document.getElementById("mensajeError");

// BUSQUEDA DE BEDELES
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
                if (!response.ok) {
                    throw new Error("Ocurrio un error inesperado intente mas tarde.");
                }
                return response.json();
            })
            .then(data => {

                // Si no hay coincidencias, informamos
                if (data.length === 0) {
                    bedelListContainer.textContent = "No se encontraron bedeles.";
                    return;
                }

                data.forEach(bedel => {
                    // Creamos el contenedor para cada bedel
                    const bedelItem = document.createElement("div");
                    bedelItem.className = "bedel-item";
                    bedelItem.style.backgroundColor = bedel.estado ? "lightgreen" : "yellow";

                    const bedelInfo = document.createElement("span");
                    bedelInfo.textContent = `"ID": ${bedel.id} | "Usuario": ${bedel.usuario} | "Nombre": ${bedel.nombre} | "Apellido": ${bedel.apellido} | "Turno": ${bedel.turno}`;
                    bedelItem.appendChild(bedelInfo);

                    // Botón para modificar
                    const modificarBtn = document.createElement("button");
                    modificarBtn.textContent = "Modificar";
                    modificarBtn.className = "modificar-btn";
                    modificarBtn.addEventListener("click", () => {
                        openModalModificar(bedel);
                    });
                    bedelItem.appendChild(modificarBtn);

                    // Botón para eliminar/activar
                    const eliminarBtn = document.createElement("button");
                    eliminarBtn.textContent = bedel.estado ? "Eliminar" : "Activar";
                    eliminarBtn.className = "eliminar-btn";
                    eliminarBtn.style.backgroundColor = bedel.estado ? "red" : "green";
                    eliminarBtn.addEventListener("click", () => {
                        toggleEstadoBedel(bedel, eliminarBtn, bedelItem);
                    });
                    bedelItem.appendChild(eliminarBtn);

                    bedelListContainer.appendChild(bedelItem);
                });
            })
            .catch(error => {
                console.error("Error:", error);
                bedelListContainer.textContent = "No se pudo cargar la lista de bedeles.";
            });
    } catch (error) {
        console.log("Error en la petición: " + error.message);
        mensajeError.textContent = "Error al realizar la petición, por favor intente más tarde.";
    }
});

const mostrarMensajeError = (mensaje) => {
    mensajeError.textContent = mensaje;
};

const limpiarMensajeError = () => {
    mensajeError.textContent = "";
};

// TOGGLE ESTADO (Activar/Eliminar)
function toggleEstadoBedel(bedel, button, bedelItem) {
    const url = bedel.estado 
        ? `/admin/api/deleteBedel/${bedel.id}` 
        : `/admin/api/activarBedel/${bedel.id}`;
    const nuevoEstado = !bedel.estado;

    fetch(url, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(errorData => {
                    throw new Error("Error al cambiar estado del bedel: " + errorData.message);
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
            alert(`Bedel ${bedel.estado ? "activado" : "eliminado"} correctamente.`);
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Ocurrió un error al intentar cambiar el estado del Bedel.");
        });
}

// FUNCIONES PARA MODAL MODIFICAR
function openModalModificar(bedel) {
    const modal = document.getElementById("modal-modificar");
    document.getElementById("bedel-id").value = bedel.id;
    document.getElementById("bedel-modificar-usuario").value = bedel.usuario;
    document.getElementById("bedel-modificar-nombre").value = bedel.nombre;
    document.getElementById("bedel-modificar-apellido").value = bedel.apellido;
    document.getElementById("bedel-modificar-turno").value = bedel.turno;
    modal.style.display = "block";
}

function closeModalModificar() {
    document.getElementById("modal-modificar").style.display = "none";
}

document.getElementById("cancel-modificar-button").addEventListener("click", closeModalModificar);

document.getElementById("save-modificar-button").addEventListener("click", () => {
    const updatedBedel = {
        id: document.getElementById("bedel-id").value,
        usuario: document.getElementById("bedel-modificar-usuario").value,
        nombre: document.getElementById("bedel-modificar-nombre").value,
        apellido: document.getElementById("bedel-modificar-apellido").value,
        turno: document.getElementById("bedel-modificar-turno").value
    };

    fetch(`/admin/api/updateBedel`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(updatedBedel)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al actualizar el Bedel.");
            }
            return response.json();
        })
        .then(() => {
            alert("Bedel actualizado correctamente.");
            closeModalModificar();
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Ocurrió un error al actualizar el Bedel.");
        });
});

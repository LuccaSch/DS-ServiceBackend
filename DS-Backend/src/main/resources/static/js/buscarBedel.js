const boton = document.getElementById('buscar');

boton.addEventListener("click", function () {
    const bedelListContainer = document.getElementById("bedel-list");
    const valorBusqueda = document.getElementById("valor_busqueda").value;
    const filtroSeleccionado = document.getElementById("filtro").value;

    limpiarMensajeError();

    // Validamos que se haya seleccionado un filtro y que el valor de búsqueda no esté vacío
    if (filtroSeleccionado !== "0" && !valorBusqueda) {
        mensajeError.textContent = "Por favor, Escriba un valor de búsqueda.";
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
                    bedelInfo.textContent = `"Usuario": ${bedel.usuario} | "Nombre": ${bedel.nombre} | "Apellido": ${bedel.apellido} | "Turno": ${bedel.turno}`;
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
                        if (confirm(`¿Estás seguro de que deseas eliminar a ${bedel.usuario}?`)) {
                            fetch(`/admin/api/deleteBedel/${bedel.usuario}`, {
                                method: "DELETE",
                            })
                                .then(response => {
                                    if (!response.ok) {
                                        throw new Error("Error al eliminar el bedel");
                                    }
                                    alert("Bedel eliminado correctamente");
                                    bedelItem.remove(); // Eliminar el elemento del DOM
                                })
                                .catch(error => {
                                    console.error("Error:", error);
                                    alert("No se pudo eliminar el bedel.");
                                });
                        }
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

const limpiarMensajeError = () => {
    mensajeError.textContent = "";
}

function openModalModificar(bedel) {
    const modal = document.getElementById("modal-bedel");

    // Cargar los datos del Bedel en el formulario
    document.getElementById("bedel-usuario").value = bedel.usuario;
    document.getElementById("bedel-nombre").value = bedel.nombre;
    document.getElementById("bedel-apellido").value = bedel.apellido;
    document.getElementById("bedel-turno").value = bedel.turno;

    // Mostrar el modal
    modal.style.display = "block";
}

// Función para cerrar el modal
function closeModal() {
    const modal = document.getElementById("modal-bedel");
    modal.style.display = "none";
}

// Guardar cambios desde el modal
document.getElementById("save-button").addEventListener("click", () => {
    const bedelId = document.getElementById("bedel-id").value;
    const usuario = document.getElementById("bedel-usuario").value;
    const nombre = document.getElementById("bedel-nombre").value;
    const apellido = document.getElementById("bedel-apellido").value;
    const turno = document.getElementById("bedel-turno").value;

    const updatedBedel = {
        id: bedelId,
        usuario: usuario,
        nombre: nombre,
        apellido: apellido,
        turno: turno
    };

    fetch(`/admin/api/updateBedel/${bedelId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(updatedBedel)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("No se pudo actualizar el Bedel.");
            }
            return response.json();
        })
        .then(data => {
            alert("Bedel actualizado correctamente.");
            closeModal(); // Cerrar el modal después de guardar
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Ocurrió un error al intentar actualizar el Bedel.");
        });
})


document.querySelector(".close-button").addEventListener("click", closeModal);


window.addEventListener("click", (event) => {
    const modal = document.getElementById("modal-bedel");
    if (event.target === modal) {
        closeModal();
    }
});

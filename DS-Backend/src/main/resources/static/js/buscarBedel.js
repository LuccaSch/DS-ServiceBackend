const boton = document.getElementById('buscar');

boton.addEventListener("click", function () {
    const bedelListContainer = document.getElementById("bedel-list");
    const valorBusqueda = document.getElementById("valor_busqueda").value;
    const filtroSeleccionado = document.getElementById("filtro").value;

    // Validamos que se haya seleccionado un filtro y que el valor de búsqueda no esté vacío
    if (filtroSeleccionado !== "0" && !valorBusqueda) {
        alert("Por favor, Escriba un valor de búsqueda.");
        return;
    }

    // Limpiar contenedor antes de cargar
    bedelListContainer.innerHTML = "";

    const filtroDatos = {
        filtro: filtroSeleccionado,
        valorBusqueda: valorBusqueda
    };

    //comienzo de la preticion
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
            // Si el filtro no tiene ninguna coincidencia informamos y terminamos la ejecucion

            if (data.length === 0) {
                bedelListContainer.textContent = "No se encontraron bedels.";
                return;
            }

            data.forEach(bedel => {
                // Creamos el contenedor para cada bedel

                const bedelItem = document.createElement("div");
                bedelItem.className = "bedel-item";

                // Segun el estado le seteamos el color (verde:"ACTIVO" , amarillo:"ELIMINADO")
                bedelItem.style.backgroundColor = bedel.estado ? "lightgreen" : "yellow";

                const bedelInfo = document.createElement("span");
                bedelInfo.textContent = `"Usuario": ${bedel.usuario} | "Nombre": ${bedel.nombre} | "Apellido": ${bedel.apellido} | "Turno": ${bedel.turno}`;
                bedelItem.appendChild(bedelInfo);

                // AGREGACION DE BOTONES

                //MODIFICAR
                const modificarBtn = document.createElement("button");
                modificarBtn.textContent = "Modificar";
                modificarBtn.className = "modificar-btn";
                modificarBtn.addEventListener("click", () => {
                    alert(`Modificar bedel con usuario: ${bedel.usuario}`);
                });
                bedelItem.appendChild(modificarBtn);

                //ELIMINAR
                const eliminarBtn = document.createElement("button");
                eliminarBtn.textContent = "Eliminar";
                eliminarBtn.className = "eliminar-btn";
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

                // Agregar el Bedel al contenedor principal
                bedelListContainer.appendChild(bedelItem);
            });
        })
        .catch(error => {
            console.error("Error:", error);
            bedelListContainer.textContent = "No se pudo cargar la lista de bedels.";
        });
});

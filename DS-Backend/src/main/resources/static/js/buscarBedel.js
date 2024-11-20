const boton = document.getElementById('buscar');

boton.addEventListener("click", function () {
    const bedelListContainer = document.getElementById("bedel-list");
    const valorBusqueda = document.getElementById("valor_busqueda").value;
    const filtroSeleccionado = document.getElementById("filtro").value;

    // Validar que se haya seleccionado un filtro y que el valor de búsqueda no esté vacío

    if (filtroSeleccionado !== "0" && !valorBusqueda) {
        // agregar un mensaje como en registrat bedel ->

        alert("Por favor, Escriba un valor de búsqueda.");
        return;
    }


    // limpiamos el conteiner
    bedelListContainer.innerHTML = "";


    const filtroDatos = {
        filtro: filtroSeleccionado,
        valorBusqueda: valorBusqueda
    };


    fetch("/admin/api/getBedel", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(filtroDatos)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al obtener la lista de bedels");
            }
            return response.json();
        })
        .then(data => {
            // Renderizarcion de los bedels en la lista

            //Preguntamos si hay concidencias
            if (data.length === 0) {
                bedelListContainer.textContent = "No se encontraron bedels.";
                return;
            }

            data.forEach(bedel => {
                const bedelItem = document.createElement("div");
                bedelItem.className = "bedel-item";
                bedelItem.textContent = `"Usuario": ${bedel.usuario} | "Nombre": ${bedel.nombre} | "Apellido": ${bedel.apellido} | "Turno": ${bedel.turno}`;
                bedelListContainer.appendChild(bedelItem);
            });
        })
        .catch(error => {
            console.error("Error:", error);
            bedelListContainer.textContent = "No se pudo cargar la lista de bedels.";
        });
});
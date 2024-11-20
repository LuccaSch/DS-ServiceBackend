const boton = document.getElementById('buscar');

boton.addEventListener("click", function () {
    const bedelListContainer = document.getElementById("bedel-list");

    fetch("/admin/api/getBedel")
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al obtener la lista de bedels");
            }
            return response.json();
        })
        .then(data => {
            // Agregar cada bedel a la lista
            data.forEach(bedel => {
                const bedelItem = document.createElement("div");
                bedelItem.className = "bedel-item";
                bedelItem.textContent = `Nombre: ${bedel.nombre}, Apellido: ${bedel.apellido}`;
                bedelListContainer.appendChild(bedelItem);
            });
        })
        .catch(error => {
            console.error("Error:", error);
            bedelListContainer.textContent = "No se pudo cargar la lista de bedels.";
        });
});

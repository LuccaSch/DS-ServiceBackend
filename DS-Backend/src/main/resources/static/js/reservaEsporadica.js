document.addEventListener("DOMContentLoaded", () => {
    const boton = document.getElementById("registrar");
    const div = document.getElementById("div-prueba");
  
    boton.addEventListener("click", function () {
      // Crear objetos de prueba
      let diaReservaDTO1 = {
        fechaReserva: "2024-12-11",
        horaInicio: "10:30:00",
        duracion: 120
      };
  
      let diaReservaDTO2 = {
        fechaReserva: "2024-12-12",
        horaInicio: "14:30:45",
        duracion: 120
      };
  
      let diaReservaDTO3 = {
        fechaReserva: "2024-12-13",
        horaInicio: "14:30:45",
        duracion: 120
      };
  
      let requeriminetoDisponibilidadDTO = {
        cantidadAlumnos: 20,
        tipoReserva: false,
        tipoAula: 0,
        diasReserva: [diaReservaDTO1, diaReservaDTO2, diaReservaDTO3]
      };
  
      try {
        // Realizar la petición fetch
        fetch("/bedel/api/getAula/esporadica", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(requeriminetoDisponibilidadDTO)
        })
          .then(response => {
            if (!(response.status === 200 || response.status === 201)) {
              return response.json().then(data => {
                throw new Error(data.message || "Error en la respuesta del servidor");
              });
            }
            return response.json();
          })
          .then(data => {
            // Mostrar la respuesta del servidor
            div.innerHTML = `
              <h4>Respuesta del servidor:</h4>
              <pre>${JSON.stringify(data, null, 2)}</pre>
            `;
          })
          .catch(error => {
            // Mostrar errores en la petición
            div.innerHTML = `
              <h4>Error:</h4>
              <pre>${error.message}</pre>
            `;
          });
      } catch (error) {
        console.log("Error en la petición: " + error.message);
      }
    });
  });
  
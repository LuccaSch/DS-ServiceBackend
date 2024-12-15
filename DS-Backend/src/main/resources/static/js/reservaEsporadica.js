/*document.addEventListener("DOMContentLoaded", () => {
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
        horaInicio: "14:30:00",
        duracion: 120
      };
  
      let diaReservaDTO3 = {
        fechaReserva: "2024-12-13",
        horaInicio: "14:30:00",
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
*/

const mensajeError = document.getElementById("mensajeError")

document.getElementById("btn-agregar-dia").addEventListener("click", agregarFechaReserva);

document.getElementById("buscar").addEventListener("click", buscarDisponibilidad);

function agregarFechaReserva(){

  const fecha =  document.getElementById("fecha-id").value;
  const horaInicio = document.getElementById("hora-inicio-id").value;
  const duracion = document.getElementById('duracion-id').value;

  // VALIDACIONES

  //campo invalido
  if (!fecha || !horaInicio || !duracion) {
    mostrarMensajeError("Todos los campos son nesesarios para agregar un dia de reserva");
    return;
  }

  //Fechas anteriores a hor
  
  const fechaSeleccionada = new Date(fecha);
  const fechaHoy = new Date();
  
  // Eliminar la hora de la fecha actual para solo comparar la parte lla fecha
  fechaHoy.setHours(0, 0, 0, 0);

  if (fechaSeleccionada < fechaHoy) {
      mostrarMensajeError("La fecha seleccionada no puede ser antes que hoy");
      return;
  }

  //Verificamos que la clase sea en modulos de 30 min

  if (duracion % 30 !== 0) {
    mostrarMensajeError("La duracion de la clase debe ser en modulos de 30 minutos, revise la duracion");
    return;
  }

  // ^ VALIDACIONES ^


  // CREAR FECHA DIA RESERVA

  limpiarMensajeError();

  const tabla = document.getElementById("dias-agregados");

  // Crear nueva fila
  const nuevaFila = document.createElement("tr");

  nuevaFila.innerHTML = `
      <td>${fecha}</td>
      <td>${horaInicio}</td>
      <td>${duracion} minutos</td>
      <td><button class="btn-eliminar">Eliminar</button></td>
  `;

  // Añadir funcionalidad al botón de eliminar
  nuevaFila.querySelector(".btn-eliminar").addEventListener("click", () => {
      tabla.removeChild(nuevaFila);
  });

  tabla.appendChild(nuevaFila);

  limpiarIngresoFecha();
};


//Buscar Disponibilidad
function buscarDisponibilidad(){
  const tabla = document.getElementById("dias-agregados");
  const filas = Array.from(tabla.querySelectorAll("tr")).slice(1); // Saltamos la cabecera

  // Div Provisorio
  const div = document.getElementById("div-prueba"); 

  if (filas.length === 0) {
      div.innerHTML = "<p>No hay fechas agregadas para enviar.</p>";
      return;
  }

  // Crear lista de objetos diaReservaDTO
  const diasReserva = filas.map(fila => {
    const columnas = fila.querySelectorAll("td");
    return {
      fechaReserva: columnas[0].textContent.trim(),
      horaInicio: columnas[1].textContent.trim() + ":00", // Aseguramos el formato HH:MM:SS
      duracion: parseInt(columnas[2].textContent.trim(), 10) // Convertir a número
    };
  });

  // Crear objeto requeriminetoDisponibilidadDTO
  const requeriminetoDisponibilidadDTO = {
    cantidadAlumnos: 20, 
    tipoReserva: false,  
    tipoAula: 0,         
    diasReserva: diasReserva
  };


  // Enviar al backend con fetch
  try {
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
}



//FUNCIONES AUXILIARES
function limpiarIngresoFecha(){
  document.getElementById("fecha-id").value = "";  
  document.getElementById("hora-inicio-id").value = "";
  document.getElementById('duracion-id').value = "";
}

const mostrarMensajeError = (mensaje) => {
  mensajeError.textContent = mensaje;
};

const limpiarMensajeError = () => {
  mensajeError.textContent = "";
}

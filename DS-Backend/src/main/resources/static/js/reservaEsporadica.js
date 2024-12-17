// ----------------------------Encabezado, Docentes y Materias---------------------------
document.addEventListener('DOMContentLoaded', () => {
  const nombreUsuario = document.getElementById('usuario-nombre');

  //Cargamos el nombre del usuario y Buscamos los docentes y aulas
  fetch('/current/api/user', {
    method: 'GET',
    credentials: 'include',
  })
  .then(response => {
    if (!response.ok) {
      response.json().then(data => {
        throw new Error(data.mensaje || 'No se pudo obtener la información del usuario por un error desconocido, por favor contactar con soporte.');
      });
    }
    return response.json();
  })
  .then(data => {
    if (data.mensaje) {
      nombreUsuario.textContent = data.mensaje;
    } else {
      console.warn('La respuesta no contiene un campo "mensaje".');
    }
  })
  .catch(error => {
    console.error('Error al obtener el usuario autenticado:', error);
  });

  const docenteSelect = document.getElementById('docente');
  const catedraSelect = document.getElementById('catedra');

  fetch('/bedel/api/getDatosCampus')
    .then(response => response.json())
    .then(data => {
      const { docentes, materias } = data;

      // Llenar el select de docentes
      docentes.forEach(docente => {
        const option = document.createElement('option');
        option.value = docente.id;
        option.textContent = docente.nombre;
        docenteSelect.appendChild(option);
      });

      // Llenar el select de catedras
      materias.forEach(materia => {
        const option = document.createElement('option');
        option.value = materia.id;
        option.textContent = materia.nombre;
        catedraSelect.appendChild(option);
      });
    })
    .catch(error => {
      console.error('Error al obtener el los profesores y las materias:', error);
    });
});


const mensajeError = document.getElementById("mensajeError");
const botonAgregarDia = document.getElementById("btn-agregar-dia");
const botonBuscar = document.getElementById("buscar");

botonAgregarDia.addEventListener("click", agregarFechaReserva);

botonBuscar.addEventListener("click", buscarDisponibilidad);

function agregarFechaReserva() {
  const fecha = document.getElementById("fecha-id").value;
  const horaInicio = document.getElementById("hora-inicio-id").value;
  const duracion = document.getElementById('duracion-id').value;

  // VALIDACIONES

  // campo invalido
  if (!fecha || !horaInicio || !duracion) {
      mostrarMensajeError("Todos los campos son necesarios para agregar un día de reserva");
      return;
  }

  // Verificamos que la clase sea en módulos de 30 min
  if (duracion % 30 !== 0) {
      mostrarMensajeError("La duración de la clase debe ser en módulos de 30 minutos, revise la duración");
      return;
  }

  // CREAR FECHA DIA RESERVA

  limpiarMensajeError();

  const tabla = document.getElementById("dias-agregados");

  const nuevaFila = document.createElement("tr");

  nuevaFila.innerHTML = `
      <td>${fecha}</td>
      <td>${horaInicio}</td>
      <td>${duracion} minutos</td>
      <td></td>
  `;

  // Crear y agregar la celda de acción
  const actionCell = crearActionCellDiasReserva(tabla.rows.length - 1); 
  nuevaFila.appendChild(actionCell);

  // Añadir la nueva fila a la tabla
  tabla.appendChild(nuevaFila);
  limpiarIngresoFecha();
}

function crearActionCellDiasReserva(index) {
  const actionCell = document.createElement("td");

  // Crear un contenedor para los botones
  const buttonContainer = document.createElement("div");
  buttonContainer.className = "button-container";

  // Botón de eliminar
  const eliminarButton = document.createElement("button");
  eliminarButton.textContent = "Eliminar";
  eliminarButton.className = "btn-cancelar-modal";

  // Añadir funcionalidad al botón de eliminar
  eliminarButton.addEventListener("click", (event) => {
      const fila = event.target.closest("tr");
      fila.remove();
  });

  // Botón de disponibilidad (oculto inicialmente)
  const disponibilidadButton = document.createElement("button");
  disponibilidadButton.textContent = "Disponibilidad";
  disponibilidadButton.className = "btn-disponibilidad-tabla";
  disponibilidadButton.id = `disponibilidad-${index}`; 

  // Añadir los botones al contenedor
  buttonContainer.appendChild(eliminarButton);
  buttonContainer.appendChild(disponibilidadButton);

  // Añadir el contenedor a la celda de acción
  actionCell.appendChild(buttonContainer);

  return actionCell;
}

function buscarDisponibilidad() {
  const tabla = document.getElementById("dias-agregados");
  const filas = Array.from(tabla.querySelectorAll("tr")).slice(1); // Saltamos la cabecera

  if (filas.length === 0) {
      mostrarMensajeError("No hay fechas agregadas para enviar.");
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

              if (!data.hasOwnProperty("resultado")) {
                  throw new Error("La respuesta del servidor no contiene la clave 'resultado'");
              }

              const disponibilidadList = data.resultado;
              // Procesar la respuesta del servidor
              disponibilidadList.forEach((disponibilidad, index) => {
                  const disponibilidadButton = document.getElementById(`disponibilidad-${index}`);

                  if (disponibilidad.superposicion) {
                      disponibilidadButton.addEventListener("click", () => mostrarSuperposicion(disponibilidad.reserva));
                  } else {
                      disponibilidadButton.addEventListener("click", () => seleccionarAula(disponibilidad.aulasCandidatas));
                  }
                  disponibilidadButton.style.display = "inline-block";
              });
          })
          .catch(error => {
              mostrarMensajeError(`Error: ${error.message}`);
          });
  } catch (error) {
      console.log("Error en la petición: " + error.message);
  }
}


function mostrarSuperposicion(reserva) {
  // Lógica para manejar la superposición
  console.log("Superposición encontrada:", reserva);
  // Aquí puedes agregar la lógica para mostrar la superposición en la interfaz de usuario
}

function seleccionarAula(aulasCandidatas) {
  // Lógica para manejar la selección de aulas
  console.log("Aulas candidatas:", aulasCandidatas);
  // Aquí puedes agregar la lógica para mostrar las aulas candidatas en la interfaz de usuario
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

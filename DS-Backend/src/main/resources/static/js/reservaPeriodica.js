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


function agregarFechaReserva(){

  const diaSemana =  document.getElementById("dia-semana-id").value;
  const horaInicio = document.getElementById("hora-inicio-id").value;
  const duracion = document.getElementById('duracion-id').value;

  // VALIDACIONES

  //campo invalido
  if (!diaSemana || !horaInicio || !duracion) {
    mostrarMensajeError("Todos los campos son nesesarios para agregar un dia de reserva");
    return;
  }

  //Verificamos que la clase sea en modulos de 30 min

  if (duracion % 30 !== 0) {
    mostrarMensajeError("La duracion de la clase debe ser en modulos de 30 minutos, revise la duracion");
    return;
  }

  // ^ VALIDACIONES ^


  // CREAR DIA SEMANA RESERVA

  limpiarMensajeError();

  const tabla = document.getElementById("dias-agregados");

  // Crear nueva fila
  const nuevaFila = document.createElement("tr");

  nuevaFila.innerHTML = `
      <td>${diaSemana}</td>
      <td>${horaInicio}</td>
      <td>${duracion} minutos</td>
      <td><button class="btn-eliminar">Eliminar</button></td>
  `;

  // Añadir funcionalidad al botón de eliminar
  nuevaFila.querySelector(".btn-eliminar").addEventListener("click", () => {
      tabla.removeChild(nuevaFila);
  });

  tabla.appendChild(nuevaFila);

  limpiarIngresoDiaSemana();
};

//Buscar Disponibilidad
function buscarDisponibilidad(){
  const tabla = document.getElementById("dias-agregados");
  
  // Saltamos la cabecera
  const filas = Array.from(tabla.querySelectorAll("tr")).slice(1); 

  // Div Provisorio
  const div = document.getElementById("div-prueba"); 

  if (filas.length === 0) {
      div.innerHTML = "<p>No hay fechas agregadas para enviar.</p>";
      return;
  }

  // Crear lista de objetos diaReservaDTO
  const diasReserva = filas.map((fila, index) => {
    const columnas = fila.querySelectorAll("td");
    return {
      id: index+1,
      diaSemana: columnas[0].textContent.trim(),
      horaInicio: columnas[1].textContent.trim() + ":00", // Aseguramos el formato HH:MM:SS
      duracion: parseInt(columnas[2].textContent.trim(), 10) // Convertir a número
    };
  });

  // Crear objeto requeriminetoDisponibilidadDTO
  const requeriminetoDisponibilidadDTO = {
    cantidadAlumnos:20, 
    tipoReserva:true,
    periodo:2,
    tipoAula:0,         
    diasReserva: diasReserva
  };

  console.log(requeriminetoDisponibilidadDTO);

  // Enviar al backend con fetch
  try {
      fetch("/bedel/api/getAula/periodica", {
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
function limpiarIngresoDiaSemana(){
  document.getElementById("dia-semana-id").value = "";  
  document.getElementById("hora-inicio-id").value = "";
  document.getElementById('duracion-id').value = "";
}
  
const mostrarMensajeError = (mensaje) => {
  mensajeError.textContent = mensaje;
};

const limpiarMensajeError = () => {
  mensajeError.textContent = "";
}

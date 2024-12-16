const mensajeError = document.getElementById("mensajeError");

document.getElementById("btn-agregar-dia").addEventListener("click", agregarFechaReserva);

document.getElementById("buscar").addEventListener("click", buscarDisponibilidad);


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
    const filas = Array.from(tabla.querySelectorAll("tr")).slice(1); // Saltamos la cabecera
  
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
      periodo:0,
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

package com.ds.tp.services;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ds.tp.exception.ReservaNoEncontradaException;
import com.ds.tp.models.aula.Aula;
import com.ds.tp.models.dto.AulaDTO;
import com.ds.tp.models.dto.DiaReservaDTO;
import com.ds.tp.models.dto.DisponibilidadAulaDTO;
import com.ds.tp.models.dto.RequerimientoDisponibilidadDTO;
import com.ds.tp.models.dto.ReservaDTO;
import com.ds.tp.models.reserva.DiaReserva;
import com.ds.tp.models.reserva.Reserva;
import com.ds.tp.repositories.AulaRepository;
import com.ds.tp.repositories.DiaReservaRepository;
import com.ds.tp.repositories.ReservaRepository;
import com.ds.tp.util.DSUtilResponseEntity;

@Service
public class AulaService {
    //ATRIBUTOS
    // Atributos inyectados por Spring 
    @Autowired
    AulaRepository aulaRepository;

    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    DiaReservaRepository diaReservaRepository;

    //Atributos propios de la clase
    private static final int INFORMATICA = 0;
    private static final int MULTIMEDIA = 1;
    private static final int SINRECURSOS = 2;

    //Constructor
    public AulaService(AulaRepository aulaRepository,ReservaRepository reservaRepository,DiaReservaRepository diaReservaRepository){
        this.aulaRepository=aulaRepository;
        this.reservaRepository=reservaRepository;
        this.diaReservaRepository=diaReservaRepository;
    }

    // FUNCIONES DEL SERVICIO AULA

    //--------------------------------------------------GET AULAS--------------------------------------------------

    public ResponseEntity<Object> getAulaEsporadica(RequerimientoDisponibilidadDTO requisito) {
        try{
            //Creanis un opcional de Aula
            Optional<? extends List<? extends Aula>> aulasOpt;

            //Filtramos las aulas por el tipo y tamaño solicitado por el usuario y ademas que esten activas
            switch (requisito.getTipoAula()) {
                case INFORMATICA -> aulasOpt = aulaRepository.findAulasInformaticaByMaximoAlumnos(requisito.getCantidadAlumnos());
                case MULTIMEDIA -> aulasOpt = aulaRepository.findAulasMultimediaByMaximoAlumnos(requisito.getCantidadAlumnos());
                case SINRECURSOS -> aulasOpt = aulaRepository.findAulasSinRecursosByMaximoAlumnos(requisito.getCantidadAlumnos());
                default -> {
                            return DSUtilResponseEntity.statusBadRequest("Tipo de aula invalido válido: Seleccione entre Informatica, Multimedia Y Sin Recursos");
                        }
            }

            //Si el opcional esta vacio no existe un aula con las caracteristicas solicitadas que este activa
            if (aulasOpt.isEmpty()) {
                return DSUtilResponseEntity.statusBadRequest("ERROR: No existe aula que cumpla con las características solicitadas");
            }

            // Hacemos una conversion explicita de tipos ya que solo nesesitamos los datos de la clase generica AULA.
            List<Aula> listaAulas = new ArrayList<>(aulasOpt.get());

            //Ordenamos de manera decendente de forma que las aulas con menor capacidad esten primeras

            listaAulas = listaAulas.stream()
                                    .sorted(Comparator.comparingInt(Aula::getMaximoAlumnos))
                                    .collect(Collectors.toList());          

            //Buscamos La disponibilidad para estas aulas en las fechas solicitadas
            List<DisponibilidadAulaDTO> disponibilidadFinal = this.getDisponibilidadFinal(listaAulas,requisito.getDiasReserva());
            
            return DSUtilResponseEntity.statusOk(disponibilidadFinal);
        }
        catch (DataAccessException e) {
            System.out.println("Error de acceso a datos: " + e.getMessage());

            return DSUtilResponseEntity.statusInternalServerError("Error interno del Servidor, por favor intentar mas tarde");
        }
        catch (Exception e) {
            System.out.println("Error de acceso a datos: " + e.getMessage());
            return DSUtilResponseEntity.statusInternalServerError("Error inesperado, por favor intentar mas tarde, si el error continua contactarse con soporte");
        }

    }


    public List<DisponibilidadAulaDTO> getDisponibilidadFinal(List<Aula> listaAulas,List<DiaReservaDTO> diasReservaDTO){
        
        List<DisponibilidadAulaDTO> disponibilidadFinal = new ArrayList<>();

        //Iteramos cada DiaReserva para encontrar la disponibilidad por ese dia en especifico
        for (DiaReservaDTO unDiaReservaDTO : diasReservaDTO){
            //Añadimos a nuestra Lista de Disponibilidad la disponibilidad de ese diaReservaDTO en particular
            disponibilidadFinal.add(getDisponibilidadDia(listaAulas,unDiaReservaDTO));
        }

        //Retornamos la disponibilidad final para todos los dias
        return disponibilidadFinal;
    }

    public DisponibilidadAulaDTO getDisponibilidadDia(List<Aula> listaAulas,DiaReservaDTO diaReservaDTO){
        //Creamos una lista de aulas para las aulasCandidatas y una lista de diasReserva para verificar las reservasSuperpuestas
        List<Aula> aulasCandidatas = new ArrayList<>();
        List<DiaReserva> reservasSuperpuestas = new ArrayList<>();

        //Traemos todos los diasReservas asociados para la fecha solicitada en el diaReservaDTO
        List<DiaReserva> diasReserva= diaReservaRepository.findByFechaReserva(diaReservaDTO.getFechaReserva());

        //Recorremos toda la lista de aulas
        for (Aula aulaPosibleCandidata : listaAulas){
            //Filtramos los diaReserva que conseguimos anteriormente preguntando por los que tengan asociada esta aula en particular

            /*  
            diasReservaFiltrada Sera en tonces el conjunto de DiasReserva, asociadas al aula actualmente iterada,
            la cual esta "Activa" y posee las caracteristicas solicitadas por el usuario
            */
            List<DiaReserva> diasReservaFiltrada= diasReserva.stream()
                                                            .filter(diaReserva -> diaReserva.getAula().equals(aulaPosibleCandidata))
                                                            .toList();

            //Si no existen diaReserva para nuestra aulaPosibleCandidata en este dia solicitado, eso la convierte automaticamente en candidata
            if(diasReservaFiltrada.isEmpty()){
                aulasCandidatas.add(aulaPosibleCandidata);
            }
            else{
                //Si no es vacia deberemos verificar si los DiasReserva se superponen con nuestro DiaReservaDTO
                Optional<DiaReserva> reservaSupuesta = existeSuperposicion(diasReservaFiltrada,diaReservaDTO);

                //Si no hay reservas superpuestas se agrega el aula a las aulas candidatas
                if(reservaSupuesta.isEmpty()){
                    aulasCandidatas.add(aulaPosibleCandidata);
                }
                else{
                    //Si hay superposicion se agrega el diaReserva a las ReservasSuperpuestas
                    reservasSuperpuestas.add(reservaSupuesta.get());
                }
            }
        }
        /*
        Si luego de iterar todas las aulas la lista de aulas candidatas sigue vacia, significa que para toda aula
        existe un Reserva con un dia de reserva que se superpone a nuestro Requerimineto de Disponibilidad (requisito)
        */
        if(aulasCandidatas.isEmpty()){
            return new DisponibilidadAulaDTO(
                        crearReservaDTO(
                            this.calcularMenorSuperposicion(reservasSuperpuestas,diaReservaDTO)
                        )
                    );
        }
        else{
            //Si por el contrario hay aulas candidatas preguntamos si existen mas de 3 para limitar las respuestas y luego las agregamos a la DisponibilidadDTO
            if (aulasCandidatas.size() > 3) {
                return new DisponibilidadAulaDTO(
                        this.crearListaAulasDTO(aulasCandidatas.subList(0, 3))
                        );
            } else {
                return new DisponibilidadAulaDTO(
                        this.crearListaAulasDTO(aulasCandidatas)
                        );
            }
        }
    }

    public Optional<DiaReserva> existeSuperposicion(List<DiaReserva> diasReservasExistentes, DiaReservaDTO diaReservaVerificar) {
        
        for (DiaReserva reservaExistente : diasReservasExistentes) {
            if(this.calcularSuperposicion(reservaExistente, diaReservaVerificar).isPresent()) {
                return Optional.of(reservaExistente);
            }
        }
    
        return Optional.empty();
    }

    public Optional<Duration> calcularSuperposicion(DiaReserva diaReserva,DiaReservaDTO diaReservaDTO) {
        //La duracion en minutos
        Duration duracion1=Duration.ofMinutes(diaReservaDTO.getDuracion());
        Duration duracion2=Duration.ofMinutes(diaReserva.getDuracion());

        LocalTime inicio1=diaReservaDTO.getHoraInicio();
        LocalTime inicio2=diaReserva.getHoraInicio();

        //el fin es el inicio+duracion
        LocalTime fin1 = inicio1.plus(duracion1);
        LocalTime fin2 = inicio2.plus(duracion2);

        // Calcular el mayor de los inicios y el menor de los finales
        LocalTime inicioSuperposicion = inicio1.isAfter(inicio2) ? inicio1 : inicio2;
        LocalTime finSuperposicion = fin1.isBefore(fin2) ? fin1 : fin2;

        // Verificar si hay superposición
        if (inicioSuperposicion.isBefore(finSuperposicion)) {
            return Optional.of(Duration.between(inicioSuperposicion, finSuperposicion));
        } else {
            return Optional.empty();
        }
    }

    public Reserva calcularMenorSuperposicion(List<DiaReserva> diasReservasSuperpuestas,DiaReservaDTO diaReservaDTO) throws ReservaNoEncontradaException{
        Duration menorSuperposicion = this.calcularSuperposicion(diasReservasSuperpuestas.getFirst(),diaReservaDTO).get();
        DiaReserva diaReservaMenosSuperpuesta = diasReservasSuperpuestas.getFirst();
        for(DiaReserva reservaSuperpuesta : diasReservasSuperpuestas){
            Duration estaSuperposicion = this.calcularSuperposicion(reservaSuperpuesta,diaReservaDTO).get();
            if(estaSuperposicion.compareTo(menorSuperposicion) < 0){
                menorSuperposicion = estaSuperposicion;
                diaReservaMenosSuperpuesta = reservaSuperpuesta;
            }
        }

        Optional<Reserva> reservaMenosSuperpuesta = reservaRepository.findById(diaReservaMenosSuperpuesta.getId());

        if(reservaMenosSuperpuesta.isEmpty()){
            throw new ReservaNoEncontradaException("ERROR: Un error interno del servidor hizo que no pueda realizar su peticion, intentelo nuevamente en unos minutos");
        }
        else{
            return reservaMenosSuperpuesta.get();
        }
    }

    public AulaDTO crearAulaDTO(Aula aula){
        return new AulaDTO(aula.getIdAula(),aula.isDisponible(), aula.getMaximoAlumnos(), aula.getPiso(), aula.getTipoPizarron());
    }

    public List<AulaDTO> crearListaAulasDTO(List<Aula> listaAulas){
        return listaAulas.stream()
                         .map(aula -> crearAulaDTO(aula))
                         .toList();
    }

    public ReservaDTO crearReservaDTO(Reserva reserva){
        return new ReservaDTO(
                    reserva.getId(),
                    reserva.getIdAsignatura(),
                    reserva.getBedel().getId(),
                    reserva.getIdDocente(),
                    reserva.getNombreAsignatura(),
                    reserva.getNombreDocente()
                    );
    }
}

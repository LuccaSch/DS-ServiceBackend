package com.ds.tp.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.CloseableThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ds.tp.models.aula.Aula;
import com.ds.tp.models.dto.DiaReservaDTO;
import com.ds.tp.models.dto.ReservaDTO;
import com.ds.tp.models.reserva.DiaReserva;
import com.ds.tp.models.reserva.Periodo;
import com.ds.tp.models.reserva.Reserva;
import com.ds.tp.models.reserva.ReservaEsporadica;
import com.ds.tp.models.reserva.ReservaPeriodica;
import com.ds.tp.models.usuario.Bedel;
import com.ds.tp.repositories.AulaRepository;
import com.ds.tp.repositories.BedelRepository;
import com.ds.tp.repositories.ReservaRepository;
import com.ds.tp.util.DSUtilResponseEntity;

@Service
public class ReservaService {
    // Atributos inyectados por Spring
    
    @Autowired
    private BedelRepository bedelRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    //Atributos propios de la clase
    private static final int PERIODICA=0;
    private static final int ESPORADICA=1; 

    public ReservaService(BedelRepository bedelRepository, AulaRepository aulaRepository,ReservaRepository reservaRepository) {
        this.bedelRepository = bedelRepository;
        this.aulaRepository=aulaRepository;
        this.reservaRepository=reservaRepository;
    }

    //Constructor

    // FUNCIONES DEL SERVICIO RESERVA

    public ResponseEntity<Object> postReserva(ReservaDTO reservaDTO){

        if(reservaDTO.getTipo()==PERIODICA){
            this.convertirPeriodica(reservaDTO);
            return crearReservaPeriodica(reservaDTO);
        }

        return crearReservaEsporadica(reservaDTO);
    }


    public void convertirPeriodica(ReservaDTO reservaDTO){
              
    }

    private ResponseEntity<Object> crearReservaEsporadica(ReservaDTO reservaDTO) {
        try{
            ReservaEsporadica nuevaReservaEsporadica = new ReservaEsporadica(this.obtenerUsuarioLogeado(),reservaDTO.getCantAlumnos(),Timestamp.from(Instant.now()) ,reservaDTO.getIdAsignatura(), reservaDTO.getIdDocente(),reservaDTO.getNombreAsignatura(),reservaDTO.getNombreDocente());

            nuevaReservaEsporadica.setDiasReserva(this.crearDiasReserva(reservaDTO.getListaDiasReservaDTO()));

            reservaRepository.save(nuevaReservaEsporadica);

            return DSUtilResponseEntity.statusOk("Se guardo la Reserva con exito");

        }
        catch (DataAccessException e) {
            return DSUtilResponseEntity.statusInternalServerError("Error interno del Servidor, por favor intentar mas tarde");
        }
        catch(IllegalStateException e){
            return DSUtilResponseEntity.statusInternalServerError(e.getMessage());
        }
        catch (Exception e) {
            return DSUtilResponseEntity.statusInternalServerError("Error inesperado, por favor intentar mas tarde, si el error continua contactarse con soporte");
        }
    }



    public ResponseEntity<Object> crearReservaPeriodica(ReservaDTO reservaDTO) {
        try{
            Optional<Periodo> periodoOptional = reservaRepository.findPeriodoById(reservaDTO.getPeriodo());
            
            if(periodoOptional.isEmpty()){
                DSUtilResponseEntity.statusInternalServerError("Se quiere asignar un periodo invalido a la reserva");
            }

            ReservaPeriodica nuevaReservaEsporadica = new ReservaPeriodica(this.obtenerUsuarioLogeado(),reservaDTO.getCantAlumnos(),Timestamp.from(Instant.now()) ,reservaDTO.getIdAsignatura(), reservaDTO.getIdDocente(),reservaDTO.getNombreAsignatura(),reservaDTO.getNombreDocente(),periodoOptional.get());

            nuevaReservaEsporadica.setDiasReserva(this.crearDiasReserva(reservaDTO.getListaDiasReservaDTO()));

            reservaRepository.save(nuevaReservaEsporadica);

            return DSUtilResponseEntity.statusOk("Se guardo la Reserva con exito");

        }
        catch (DataAccessException e) {
            return DSUtilResponseEntity.statusInternalServerError("Error interno del Servidor, por favor intentar mas tarde");
        }
        catch(IllegalStateException e){
            return DSUtilResponseEntity.statusInternalServerError(e.getMessage());
        }
        catch (Exception e) {
            return DSUtilResponseEntity.statusInternalServerError("Error inesperado, por favor intentar mas tarde, si el error continua contactarse con soporte");
        }
    }



    public List<DiaReserva> crearDiasReserva(List<DiaReservaDTO> listaDiasReservaDTO){
        List<DiaReserva> diasReserva = new ArrayList<>();

        for(DiaReservaDTO diaReservaDTO : listaDiasReservaDTO){
            diasReserva.add(crearDiaReserva(diaReservaDTO));
        }

        return diasReserva;
    }

    public DiaReserva crearDiaReserva(DiaReservaDTO diaReservaDTO){
        Optional<Aula> aulaOptional = aulaRepository.findById(diaReservaDTO.getIdAula());

        if(aulaOptional.isPresent()){
            return new DiaReserva(aulaOptional.get(), diaReservaDTO.getDuracion(), diaReservaDTO.getFechaReserva(), diaReservaDTO.getHoraInicio());
        }
        
        throw new IllegalStateException("ERROR: Se pide crear una reserva de un Aula inexistente");
    }

    public Bedel obtenerUsuarioLogeado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("ERROR: No hay un usuario autenticado.");
        }
        // Sabemos que siempre será UserDetails porque usamos UserDetailsService
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Optional<Bedel> opcionalBedel =  bedelRepository.findByUsuario(userDetails.getUsername());

        if(opcionalBedel.isPresent()){
            return opcionalBedel.get();
        }

        throw new IllegalStateException("ERROR: No existe el usuario Solicitado");
    }
}

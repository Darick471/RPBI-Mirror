package com.expedienteclinico.expedienteclinico.services.appointments;

import com.expedienteclinico.expedienteclinico.models.appointments.AppointmentsModel;
import com.expedienteclinico.expedienteclinico.repositories.appointments.IAppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentsService {

    @Autowired
    private IAppointmentsRepository iAppointmentsRepository;

    public List<AppointmentsModel> listarTodas() {
        return iAppointmentsRepository.findAll();
    }

    public AppointmentsModel guardarCita(AppointmentsModel cita) {
        if (cita.getEstado() == null) cita.setEstado("PENDIENTE");
        return iAppointmentsRepository.save(cita);
    }

    public void eliminarCita(Long id) {
        iAppointmentsRepository.deleteById(id);
    }

    public List<AppointmentsModel> buscarPorNombrePaciente(String nombre) {
        return iAppointmentsRepository.findByPacienteNombre(nombre);
    }

    public AppointmentsModel actualizarCita(Long id, AppointmentsModel citaActualizada) {
        // Primero, buscamos si la cita existe en la base de datos usando Optional
        Optional<AppointmentsModel> citaExistente = iAppointmentsRepository.findById(id);

        if (citaExistente.isPresent()) {
            // Si existe, sacamos el modelo viejo
            AppointmentsModel cita = citaExistente.get();

            // Actualizamos los campos con los datos nuevos que llegaron
            cita.setPacienteNombre(citaActualizada.getPacienteNombre());
            cita.setEspecialista(citaActualizada.getEspecialista());
            cita.setFecha(citaActualizada.getFecha());
            cita.setHora(citaActualizada.getHora());
            cita.setMotivo(citaActualizada.getMotivo());
            cita.setEstado(citaActualizada.getEstado());

            return iAppointmentsRepository.save(cita);
        } else {

            return null;
        }
    }
}
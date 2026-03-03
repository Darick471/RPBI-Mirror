package com.expedienteclinico.expedienteclinico.services.Patients;

import com.expedienteclinico.expedienteclinico.beans.Patients.AppointmentsBean;
import com.expedienteclinico.expedienteclinico.models.patients.PatientsAppointmentsModel;
import com.expedienteclinico.expedienteclinico.models.patients.PatientsModel; // Revisa que este import coincida con tu modelo real
import com.expedienteclinico.expedienteclinico.repositories.Patients.IPatientsAppointmentsRepository;
import com.expedienteclinico.expedienteclinico.repositories.Patients.IPatientsRepository; // Asumo que así se llama tu repo de pacientes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PatientsAppointmentsService {

    @Autowired
    private IPatientsAppointmentsRepository repository;

    // Inyectamos el repo de pacientes para poder buscar al paciente cuando creamos una cita
    @Autowired
    private IPatientsRepository patientsRepository;

    // 1. GET: Obtener lista convertida a DTO
    public List<AppointmentsBean> getAllAppointments() {
        List<PatientsAppointmentsModel> entidades = repository.findAll();
        List<AppointmentsBean> dtos = new ArrayList<>();

        for (PatientsAppointmentsModel entidad : entidades) {
            AppointmentsBean dto = new AppointmentsBean();
            dto.setId(entidad.getId());
            dto.setFechaHoraInicio(entidad.getFechaHoraInicio());
            dto.setFechaHoraFin(entidad.getFechaHoraFin());
            dto.setEstado(entidad.getEstado());
            dto.setConsultorio(entidad.getConsultorio());

            if (entidad.getPaciente() != null) {
                dto.setPacienteId(entidad.getPaciente().getId());  // UUID → UUID (funciona)

            }
            dtos.add(dto);
        }
        return dtos;
    }

    // 2. POST: Crear una cita nueva vinculada a un paciente
    public Object nuevo(AppointmentsBean bean) {
        PatientsAppointmentsModel entidad = new PatientsAppointmentsModel();

        entidad.setFechaHoraInicio(bean.getFechaHoraInicio());
        entidad.setFechaHoraFin(bean.getFechaHoraFin());
        entidad.setEstado(bean.getEstado());
        entidad.setConsultorio(bean.getConsultorio());

        if (bean.getPacienteId() != null) {
            PatientsModel paciente = patientsRepository.findById(bean.getPacienteId())
                    .orElseThrow(() -> new RuntimeException("Error: El paciente con ID " + bean.getPacienteId() + " no existe."));
            entidad.setPaciente(paciente);
        } else {
            throw new RuntimeException("Error: Es obligatorio enviar el pacienteId para crear una cita.");
        }

        return repository.save(entidad);
    }

    // 3. GET: Obtener todos en crudo (Para el método "all")
    public List<PatientsAppointmentsModel> getAll() {
        return repository.findAll();
    }

    // 4. PUT: Actualizar una cita existente
    public ResponseEntity<?> updateData(AppointmentsBean bean, BindingResult result, UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        PatientsAppointmentsModel citaActual = repository.findById(id).orElse(null);

        if (citaActual == null) {
            response.put("mensaje", "Error: no se pudo editar, la cita ID: " + id + " no existe.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            citaActual.setFechaHoraInicio(bean.getFechaHoraInicio());
            citaActual.setFechaHoraFin(bean.getFechaHoraFin());
            citaActual.setEstado(bean.getEstado());
            citaActual.setConsultorio(bean.getConsultorio());

            // Si se envió un nuevo ID de paciente, lo actualizamos
            if (bean.getPacienteId() != null && !bean.getPacienteId().equals(citaActual.getPaciente().getId())) {
                PatientsModel nuevoPaciente = patientsRepository.findById(bean.getPacienteId())
                        .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
                citaActual.setPaciente(nuevoPaciente);
            }

            repository.save(citaActual);

            response.put("mensaje", "La cita ha sido actualizada con éxito");
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            response.put("mensaje", "Error al actualizar la cita en la base de datos");
            response.put("error", "Ocurrió un error interno en el servidor.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
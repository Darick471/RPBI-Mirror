package com.expedienteclinico.expedienteclinico.services.Patients;

import com.expedienteclinico.expedienteclinico.beans.Patients.ClinicHistoryBean;
import com.expedienteclinico.expedienteclinico.models.patients.ClinicHistoryModel; // O ClinicHistoryModel, según como lo nombraste
import com.expedienteclinico.expedienteclinico.models.patients.PatientsModel;
import com.expedienteclinico.expedienteclinico.repositories.Patients.IClinicHistoryRepository;
import com.expedienteclinico.expedienteclinico.repositories.Patients.IPatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClinicHistoryService {

    @Autowired
    private IClinicHistoryRepository repository;

    @Autowired
    private IPatientsRepository patientsRepository;

    // 1. GET: Obtener lista convertida a DTO
    public List<ClinicHistoryBean> getAllClinicHistory() {
        List<ClinicHistoryModel> entidades = repository.findAll();
        List<ClinicHistoryBean> dtos = new ArrayList<>();

        for (ClinicHistoryModel entidad : entidades) {
            ClinicHistoryBean dto = new ClinicHistoryBean();
            dto.setPacienteId(entidad.getId());
            dto.setFechaRegistro(entidad.getFechaRegistro());
            dto.setMotivoConsulta(entidad.getMotivoConsulta());
            dto.setEnfermedadActual(entidad.getPadecimientoActual());
            dto.setDiagnosticoPreliminar(entidad.getDiagnosticoPreliminar());

            if (entidad.getPaciente() != null) {
                dto.setPacienteId(entidad.getPaciente().getId());
            }
            dtos.add(dto);
        }
        return dtos;
    }

    // 2. POST: Crear un nuevo historial vinculado a un paciente
    public Object nuevo(ClinicHistoryBean bean) {
        ClinicHistoryModel entidad = new ClinicHistoryModel();

        entidad.setFechaRegistro(bean.getFechaRegistro());
        entidad.setMotivoConsulta(bean.getMotivoConsulta());
        entidad.setPadecimientoActual(bean.getEnfermedadActual());
        entidad.setDiagnosticoPreliminar(bean.getDiagnosticoPreliminar());

        if (bean.getPacienteId() != null) {
            PatientsModel paciente = patientsRepository.findById(bean.getPacienteId())
                    .orElseThrow(() -> new RuntimeException("Error: El paciente con ID " + bean.getPacienteId() + " no existe."));
            entidad.setPaciente(paciente);
        } else {
            throw new RuntimeException("Error: Es obligatorio enviar el pacienteId para el historial clínico.");
        }

        return repository.save(entidad);
    }

    // 3. GET: Obtener todos en crudo (Para el método "all")
    public List<ClinicHistoryModel> getAll() {
        return repository.findAll();
    }

    // 4. PUT: Actualizar un historial existente
    public ResponseEntity<?> updateData(@Valid ClinicHistoryModel bean, BindingResult result, UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        ClinicHistoryModel historialActual = repository.findById(id).orElse(null);

        if (historialActual == null) {
            response.put("mensaje", "Error: no se pudo editar, el historial ID: " + id + " no existe.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            historialActual.setFechaRegistro(bean.getFechaRegistro());
            historialActual.setMotivoConsulta(bean.getMotivoConsulta());
            historialActual.setPadecimientoActual(bean.getPadecimientoActual());
            historialActual.setDiagnosticoPreliminar(bean.getDiagnosticoPreliminar());

            if (bean.getId() != null && !bean.getId().equals(historialActual.getPaciente().getId())) {
                PatientsModel nuevoPaciente = patientsRepository.findById(bean.getId())
                        .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
                historialActual.setPaciente(nuevoPaciente);
            }

            repository.save(historialActual);

            response.put("mensaje", "El historial ha sido actualizado con éxito");
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            response.put("mensaje", "Error al actualizar el historial en la base de datos");
            response.put("error", "Ocurrió un error interno en el servidor.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
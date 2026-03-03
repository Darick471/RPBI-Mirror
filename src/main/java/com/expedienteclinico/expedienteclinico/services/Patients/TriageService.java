package com.expedienteclinico.expedienteclinico.services.Patients;

import com.expedienteclinico.expedienteclinico.beans.Patients.TriageBean;
import com.expedienteclinico.expedienteclinico.models.patients.PatientsModel;
import com.expedienteclinico.expedienteclinico.models.patients.TriageModel;
import com.expedienteclinico.expedienteclinico.repositories.Patients.ITriageRepository; // O TriageRepository
import com.expedienteclinico.expedienteclinico.repositories.Patients.IPatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TriageService {

    @Autowired
    private ITriageRepository repository;

    @Autowired
    private IPatientsRepository patientsRepository;

    // 1. GET: Obtener lista convertida a DTO
    public List<TriageBean> getAllTriage() { // Revisa que en el controller lo llames así exactamente
        List<TriageModel> entidades = repository.findAll();
        List<TriageBean> dtos = new ArrayList<>();

        for (TriageModel entidad : entidades) {
            TriageBean dto = new TriageBean();
            dto.setFechaHora(entidad.getFechaHora());
            dto.setTemperatura(entidad.getTemperatura());
            dto.setPresionArterial(entidad.getPresionArterial());
            dto.setFrecuenciaCardiaca(entidad.getFrecuenciaCardiaca());
            dto.setSaturacionOxigeno(entidad.getSaturacionOxigeno());
            dto.setNivel(entidad.getNivel());

            if (entidad.getPaciente() != null) {
                dto.setPacienteId (entidad.getPaciente().getId());  // Long
            }
            dtos.add(dto);
        }
        return dtos;
    }

    // 2. POST: Crear un nuevo triaje vinculado a un paciente
    public Object nuevo(TriageBean bean) {
        TriageModel entidad = new TriageModel();

        entidad.setFechaHora(bean.getFechaHora());
        entidad.setTemperatura(bean.getTemperatura());
        entidad.setPresionArterial(bean.getPresionArterial());
        entidad.setFrecuenciaCardiaca(bean.getFrecuenciaCardiaca());
        entidad.setSaturacionOxigeno(bean.getSaturacionOxigeno());
        entidad.setNivel(bean.getNivel());

        if (bean.getPacienteId() != null) {
            PatientsModel paciente = patientsRepository.findById(bean.getPacienteId())
                    .orElseThrow(() -> new RuntimeException("Error: El paciente con ID " + bean.getPacienteId() + " no existe."));
            entidad.setPaciente(paciente);
        } else {
            throw new RuntimeException("Error: Es obligatorio enviar el pacienteId para el triaje.");
        }

        return repository.save(entidad);
    }

    // 3. GET: Obtener todos en crudo (Para el método "all")
    public List<TriageModel> getAll() {
        return repository.findAll();
    }

    // 4. PUT: Actualizar un triaje existente
    public ResponseEntity<?> updateData(TriageBean bean, BindingResult result, UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        TriageModel triajeActual = repository.findById(id).orElse(null);

        if (triajeActual == null) {
            response.put("mensaje", "Error: no se pudo editar, el triaje ID: " + id + " no existe.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            triajeActual.setFechaHora(bean.getFechaHora());
            triajeActual.setTemperatura(bean.getTemperatura());
            triajeActual.setPresionArterial(bean.getPresionArterial());
            triajeActual.setFrecuenciaCardiaca(bean.getFrecuenciaCardiaca());
            triajeActual.setSaturacionOxigeno(bean.getSaturacionOxigeno());
            triajeActual.setNivel(bean.getNivel());

            if (bean.getPacienteId() != null && !bean.getPacienteId().equals(triajeActual.getPaciente().getId())) {
                PatientsModel nuevoPaciente = patientsRepository.findById(bean.getPacienteId())
                        .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
                triajeActual.setPaciente(nuevoPaciente);
            }

            repository.save(triajeActual);

            response.put("mensaje", "El triaje ha sido actualizado con éxito");
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            response.put("mensaje", "Error al actualizar el triaje en la base de datos");
            response.put("error", "Ocurrió un error interno en el servidor.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
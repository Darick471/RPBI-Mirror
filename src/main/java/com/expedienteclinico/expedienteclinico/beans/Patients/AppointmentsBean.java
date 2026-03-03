package com.expedienteclinico.expedienteclinico.beans.Patients;

import com.expedienteclinico.expedienteclinico.models.patients.StatusAppointment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class AppointmentsBean {
    private UUID pacienteId;  // Solo pedimos el ID del paciente
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private StatusAppointment estado;
    private String consultorio;

    public void setId(UUID id) {
    }
}
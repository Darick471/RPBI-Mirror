package com.expedienteclinico.expedienteclinico.beans.Patients;



import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ClinicHistoryBean {
    private UUID pacienteId;  // Solo pedimos el ID del paciente
    private LocalDateTime fechaRegistro;
    private String motivoConsulta;
    private String enfermedadActual;
    private String diagnosticoPreliminar;

}
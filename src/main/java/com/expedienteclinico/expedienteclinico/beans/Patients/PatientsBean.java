package com.expedienteclinico.expedienteclinico.beans.Patients;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientsBean {
    private UUID pacienteId;            // ← CAMBIO: Long → UUID
    private String nombre;
    private String apellidos;
    private String curp;
    private LocalDate fechaNacimiento;
    private String genero;
    private String telefono;
    private String email;
    private String direccion;
    private String tipoSangre;
}
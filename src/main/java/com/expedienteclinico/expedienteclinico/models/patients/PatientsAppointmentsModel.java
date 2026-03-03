package com.expedienteclinico.expedienteclinico.models.patients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointments") // Ya no marcará error al borrar el import de Hibernate
@Data
@NoArgsConstructor // Obligatorio para JPA
@AllArgsConstructor
public class PatientsAppointmentsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private UUID id;  // ← CAMBIO: Long → UUID

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientsModel paciente; // Ojo: Asegúrate de que tu modelo se llame PatientsModel y no PacienteModel

    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    @Enumerated(EnumType.STRING)
    private StatusAppointment estado;

    private String consultorio;
}
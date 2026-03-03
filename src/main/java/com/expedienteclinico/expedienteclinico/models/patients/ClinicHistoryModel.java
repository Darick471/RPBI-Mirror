package com.expedienteclinico.expedienteclinico.models.patients;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "clinic_history")
@Data
public class ClinicHistoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private UUID id;  // ← CAMBIO: Long → UUID

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientsModel paciente;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro;

    @Column(length = 2000)
    private String motivoConsulta;

    @Column(length = 5000)
    private String padecimientoActual;

    private String diagnosticoPreliminar;

    // Relación con el médico que atendió (Ver punto 5)
    // private Long medicoId;
}
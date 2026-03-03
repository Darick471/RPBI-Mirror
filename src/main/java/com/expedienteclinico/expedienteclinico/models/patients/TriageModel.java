package com.expedienteclinico.expedienteclinico.models.patients;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "triages")
@Data
@NoArgsConstructor // ¡Esta es la línea mágica que quita el error de @Entity!
@AllArgsConstructor
public class TriageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private UUID id;  // ← CAMBIO: Long → UUID

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientsModel paciente; // Asegúrate de que la clase se llama exactamente Patient

    private LocalDateTime fechaHora;

    // Signos Vitales
    private Double temperatura;
    private String presionArterial; // Ej: "120/80"
    private Integer frecuenciaCardiaca;
    private Integer saturacionOxigeno;

    // Escala de urgencia (Ej: 1 a 5, o colores)
    @Enumerated(EnumType.STRING)
    private UrgencyLevel nivel;
}
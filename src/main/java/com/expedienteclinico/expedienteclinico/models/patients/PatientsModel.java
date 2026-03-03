package com.expedienteclinico.expedienteclinico.models.patients;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PatientsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private UUID id;  // ← CAMBIO: Long → UUID

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(unique = true, nullable = false)
    private String curp; // O DNI/Cédula

    private LocalDate fechaNacimiento;

    private String genero; // M, F, X

    private String telefono;
    private String email;
    private String direccion;
    private String tipoSangre;

    // Relación con el Historial (Uno a muchos)
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClinicHistoryModel> historiales;
}
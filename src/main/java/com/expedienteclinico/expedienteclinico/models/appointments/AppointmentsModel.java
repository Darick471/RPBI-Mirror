package com.expedienteclinico.expedienteclinico.models.appointments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "citas")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AppointmentsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pacienteNombre;
    private String especialista;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private String estado;
}
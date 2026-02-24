package com.expedienteclinico.expedienteclinico.models.rpbi;

import com.expedienteclinico.expedienteclinico.models.StatusModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rpbi_generation_record")
@Getter
@Setter
public class RpbiGenerationRecordModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relaciones con los catálogos normativos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classification_id", nullable = false)
    private RpbiClasificationModel classification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "physical_state_id", nullable = false)
    private RpbiPhysicalStateModel physicalState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "container_id", nullable = false)
    private RpbiContainerModel container;

    @Column(nullable = false)
    private Double quantity; // Peso o volumen

    @Column(name = "generation_date", nullable = false)
    private LocalDateTime generationDate;

    @Column(name = "generation_area", length = 100, nullable = false)
    private String generationArea; // Ej. "Quirófano 1"

    @Column(name = "responsible_user", length = 100, nullable = false)
    private String responsibleUser; // Quien registra el residuo

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estatus_id", nullable = false)
    private StatusModel status;
}
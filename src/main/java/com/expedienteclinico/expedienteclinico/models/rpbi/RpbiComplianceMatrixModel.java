package com.expedienteclinico.expedienteclinico.models.rpbi;

import com.expedienteclinico.expedienteclinico.models.StatusModel;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "rpbi_compliance_matrix_nom087")
@Getter
@Setter
public class RpbiComplianceMatrixModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relaciones estrictas (LAZY para optimizar el grafo de memoria)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clasificacion_id", nullable = false)
    private RpbiClasificationModel classification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_fisico_id", nullable = false)
    private RpbiPhysicalStateModel physicalState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "envase_id", nullable = false)
    private RpbiContainerModel container;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estatus_id", nullable = false)
    private StatusModel status;
}
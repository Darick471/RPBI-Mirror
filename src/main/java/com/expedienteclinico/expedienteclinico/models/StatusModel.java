package com.expedienteclinico.expedienteclinico.models;
//Si documentas el modelo, Swagger mostrará ejemplos automáticos en la sección de Schemas (abajo en la web).
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "status")
@Getter
@Setter
public class StatusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status", nullable = false) // Buena práctica: nombrar explícitamente la PK
    @Schema(description = "Identificador único en la base de datos", example = "101")
    private Long id;

    @Column(name = "uuid", updatable = false, nullable = false, unique = true, length = 36)
    private String uuid = java.util.UUID.randomUUID().toString();

    @Column(name = "name", nullable = false, unique = true)
    @Schema(description = "Nombre descriptivo del estado", example = "ACTIVO", allowableValues = {"ACTIVO", "INACTIVO", "PENDIENTE"})
    private String statusName;
}

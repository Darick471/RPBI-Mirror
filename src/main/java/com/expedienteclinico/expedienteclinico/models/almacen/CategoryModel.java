package com.expedienteclinico.expedienteclinico.models.almacen;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Schema(description = "Modelo que representa la categoría de artículos en el almacén (Farmacia, Clínica o Equipamiento Médico)")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la categoría", example = "1")
    private Long id;

    @Column(length = 50, nullable = false)
    @Schema(description = "Nombre de la categoría", example = "Equipo medico", allowableValues = {"Farmacia", "Materiales de Curacion", "Equipo Medico"})
    private String name;

    @Column(columnDefinition = "TEXT")
    @Schema(description = "Descripción detallada de lo que incluye esta categoría", example = "Todas las maquinas especializadas y herramientas medicas.")
    private String description;


}
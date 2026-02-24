package com.expedienteclinico.expedienteclinico.beans.rpbi;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RpbiGenerationRecordRequestObject {

    @NotBlank(message = "Se requiere el UUID de la clasificación.")
    private String classificationUuid;

    @NotBlank(message = "Se requiere el UUID del estado físico.")
    private String physicalStateUuid;

    @NotBlank(message = "Se requiere el UUID del contenedor.")
    private String containerUuid;

    @NotNull(message = "Se requiere la cantidad.")
    @Min(value = 0, message = "La cantidad debe ser mayor a cero.")
    private Double quantity;

    @NotBlank(message = "Se requiere una área de generación.")
    private String generationArea;

    @NotBlank(message = "Se requiere un usuario responsable.")
    private String responsibleUser;
}
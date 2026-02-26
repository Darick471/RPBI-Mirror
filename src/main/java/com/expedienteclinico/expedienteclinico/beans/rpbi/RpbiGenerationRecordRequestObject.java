package com.expedienteclinico.expedienteclinico.beans.rpbi;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class RpbiGenerationRecordRequestObject {

    private static final String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
    private static final String UUID_MSG = "El formato del UUID no es válido.";

    @NotBlank(message = "Se requiere el UUID de la clasificación.")
    @Pattern(regexp = UUID_REGEX, message = UUID_MSG)
    private String classificationUuid;

    @NotBlank(message = "Se requiere el UUID del estado físico.")
    @Pattern(regexp = UUID_REGEX, message = UUID_MSG)
    private String physicalStateUuid;

    @NotBlank(message = "Se requiere el UUID del contenedor.")
    @Pattern(regexp = UUID_REGEX, message = UUID_MSG)
    private String containerUuid;

    @NotNull(message = "Se requiere la cantidad.")
    @Min(value = 0, message = "La cantidad debe ser mayor a cero.")
    private Double quantity;

    @NotBlank(message = "Se requiere una área de generación.")
    private String generationArea;

    @NotBlank(message = "Se requiere un usuario responsable.")
    private String responsibleUser;
}
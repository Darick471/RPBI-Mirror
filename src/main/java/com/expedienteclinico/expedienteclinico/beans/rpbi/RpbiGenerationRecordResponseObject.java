package com.expedienteclinico.expedienteclinico.beans.rpbi;

import lombok.Data;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;


@Data
public class RpbiGenerationRecordResponseObject {
    private String recordUuid; // Identificador público de la transacción
    private String classificationName; // Ej. "Punzocortantes"
    private String physicalStateName;  // Ej. "Sólido"
    private String containerName;      // Ej. "Recipiente Rígido Rojo"
    private Double quantity;
    private String generationArea;
    private String responsibleUser;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime generationDate;
    private String statusName;
}
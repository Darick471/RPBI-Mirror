package com.expedienteclinico.expedienteclinico.beans.rpbi;
import lombok.Data;

@Data
public class RpbiPhysicalStateObject {
    private Long id;
    private String nombre;
    private String unidadMedida;
    private Long estatusId;
    private String estatusNombre;
}

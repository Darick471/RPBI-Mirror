package com.expedienteclinico.expedienteclinico.beans.rpbi;
import lombok.Data;

@Data
public class RpbiPhysicalStateObject {
    private String uuid;
    private String nombre;
    private String unidadMedida;
    private Long estatusId;
    private String estatusNombre;
}

package com.expedienteclinico.expedienteclinico.beans.rpbi;
import lombok.Data;

@Data
public class RpbiContainerObject {
    private String uuid;
    private String nombre;
    private String descripcion;
    private Long estatusId;
    private String estatusNombre;
}

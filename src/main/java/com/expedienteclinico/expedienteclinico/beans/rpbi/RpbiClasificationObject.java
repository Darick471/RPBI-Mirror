package com.expedienteclinico.expedienteclinico.beans.rpbi;
import lombok.Data;

@Data
public class RpbiClasificationObject {
    private String uuid;
    private String nombre;
    private String descripcion;
    private String codigoColor;
    private String statusUuid;
    private String estatusNombre;
}

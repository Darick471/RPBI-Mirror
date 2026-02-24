package com.expedienteclinico.expedienteclinico.beans.rpbi;
import lombok.Data;

@Data
public class RpbiClasificationObject {
    private Long id;
    private String nombre;
    private String descripcion;
    private String codigoColor;
    private Long estatusId;
    private String estatusNombre;
}

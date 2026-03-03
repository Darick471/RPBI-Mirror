package com.expedienteclinico.expedienteclinico.beans.lyr;

import lombok.Data;

@Data
public class LyR_EmployeesObject {

    private Long id_empleado;
    private String nombre;
    private String apellido;
    private String area;
    private String turno;
    private Integer telefono;
    private Long StatusId;
    private String StatusName;
}

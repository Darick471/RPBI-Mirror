package com.expedienteclinico.expedienteclinico.beans.lyr;

import lombok.Data;

@Data
public class Textile_movementsObject {
    private Long id_motion;
    private String Type_motion;
    private Integer amount;
    private String motion_date;
    private String observations;
    private String employeesNombre;
    private Long articuloId;
}

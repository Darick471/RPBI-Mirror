package com.expedienteclinico.expedienteclinico.beans.lyr;

import lombok.Data;

@Data
public class Cleaning_suppliesObject {
    private Long id_supplies;
    private String name;
    private String unit_measurement;
    private Integer stock_min;
    private Integer current_stock;
    private String expiration_date;
    private Long statusId;
    private String statusNombre;
}

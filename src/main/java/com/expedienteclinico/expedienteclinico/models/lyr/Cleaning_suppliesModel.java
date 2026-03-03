package com.expedienteclinico.expedienteclinico.models.lyr;

import com.expedienteclinico.expedienteclinico.models.StatusModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Cleaning_suppliesModel {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    @Column( nullable = false )
    private Long id_supplies;

    @Column(nullable = false, length = 100)
    private String name; // (Cloro, Desinfectante, Alcohol)

    @Column
    private String unit_measurement; //(Kg, L, Ml,)

    @Column
    private Integer current_stock;

    @Column
    private Integer stock_min;

    @Column
    private String expiration_date;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
   private StatusModel Status;
}



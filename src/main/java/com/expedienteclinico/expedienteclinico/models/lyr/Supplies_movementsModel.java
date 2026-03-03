package com.expedienteclinico.expedienteclinico.models.lyr;

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
public class Supplies_movementsModel {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    @Column(name="id_movimiento" , nullable = false )
    private Long id_motion;

    @Column(name="tipo_movimiento")
    private String type_motion; //(Entrada, Consumo, ajuste)

    @Column(name="cantidad")
    private Integer amount;

    @Column(name= "fecha_movimiento")
    private String motion_date;

    @Column( name = "observaciones" ,columnDefinition = "TEXT")
    private String observations;

    @ManyToOne
    @JoinColumn(name = "supplies_id", nullable = false)
    private Cleaning_suppliesModel supplies;

    @ManyToOne
    @JoinColumn(name = "employees_id", nullable = false)
    private LyR_EmployeesModel employees;

}

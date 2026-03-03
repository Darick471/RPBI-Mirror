package com.expedienteclinico.expedienteclinico.models.lyr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Textile_movementsModel {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    @Column( nullable = false )
    private Long id_motion;

    @Column
    private String type_motion; //(Entrada, Salida, Lavado, Baja)

    @Column
    private Integer amount;

    @Column
    private String motion_date;

    @Column
    private String observations;

    @ManyToOne
    @JoinColumn(name="articulo_id" , nullable = false)
    private Textile_articlesModel textile;

    @ManyToOne
    @JoinColumn(name = "empleado_id" , nullable = false)
    private LyR_EmployeesModel empleado;
}

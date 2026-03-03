package com.expedienteclinico.expedienteclinico.models.lyr;

import com.expedienteclinico.expedienteclinico.models.StatusModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Table;

import jakarta.persistence.*;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LyR_EmployeesModel {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    @Column( nullable = false )
    private Long id_empleado ;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String area;

    @Column
    private String turno;

    @Column
    private Integer telefono;

    @ManyToOne
    @JoinColumn(name = "status_id" , nullable = false)
    private StatusModel Status;
}



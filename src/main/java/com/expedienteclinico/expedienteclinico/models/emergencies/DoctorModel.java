package com.expedienteclinico.expedienteclinico.models.emergencies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;


@Entity
@Table( name = "first_model" )
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DoctorModel {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    @Column( nullable = false )
    private Long id ;
    private String nombre ;
    private String apellido ;

}

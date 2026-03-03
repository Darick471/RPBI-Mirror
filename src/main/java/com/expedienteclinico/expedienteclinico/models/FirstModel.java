package com.expedienteclinico.expedienteclinico.models;

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

public class FirstModel {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    @Column( nullable = false )
    private Long id ;
    private String nombre ;
    private String apellido ;

}
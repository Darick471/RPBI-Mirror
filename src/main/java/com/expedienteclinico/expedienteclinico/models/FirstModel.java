package com.expedienteclinico.expedienteclinico.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@Deprecated
@Entity
@Table( appliesTo = "first_model" )
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Deprecated
public class FirstModel {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    @Column( nullable = false )
    private Long id ;
    private String nombre ;
    private String apellido ;

}

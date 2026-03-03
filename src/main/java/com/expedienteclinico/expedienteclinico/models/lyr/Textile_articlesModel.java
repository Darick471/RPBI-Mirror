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
public class Textile_articlesModel {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    @Column( nullable = false )

    private Long id_articles;

    @Column
    private String name;

    @Column
    private String descripcion;

    @ManyToOne
    @JoinColumn(name="status_id" , nullable = false)
    private StatusModel Status;
}



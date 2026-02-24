package com.expedienteclinico.expedienteclinico.models.rpbi;

import com.expedienteclinico.expedienteclinico.models.StatusModel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "rpbi_cat_clasification")
@Getter
@Setter
public class RpbiClasificationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", updatable = false, nullable = false, unique = true, length = 36)
    private String uuid = java.util.UUID.randomUUID().toString();

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(name = "color_code", length = 20)
    private String colorCode;

    // Columna equivalente de FK a StatusModel instanciandolo abajo
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusModel status;
    //          ↑ Aquí
}
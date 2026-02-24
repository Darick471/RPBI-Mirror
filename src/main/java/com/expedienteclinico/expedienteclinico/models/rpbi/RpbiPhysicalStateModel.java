package com.expedienteclinico.expedienteclinico.models.rpbi;

import com.expedienteclinico.expedienteclinico.models.StatusModel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "rpbi_cat_phyisical_state")
@Getter
@Setter
public class RpbiPhysicalStateModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name; // "Sólido", "Líquido"

    @Column(nullable = false, length = 10)
    private String measureUnit; // "kg", "L" (Simplificamos aquí para no crear otra tabla)

    // Relación obligatoria con Estatus
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusModel status;
}
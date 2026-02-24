package com.expedienteclinico.expedienteclinico.models.rpbi;

import com.expedienteclinico.expedienteclinico.models.StatusModel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "rpbi_cat_containers")
@Getter
@Setter
public class RpbiContainerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", updatable = false, nullable = false, unique = true, length = 36)
    private String uuid = java.util.UUID.randomUUID().toString();

    @Column(nullable = false, length = 100)
    private String name; // "Bolsa de Polietileno", "Recipiente Rígido"

    @Column(length = 255)
    private String description; // "Impermeable, calibre 200..."

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusModel status;
}
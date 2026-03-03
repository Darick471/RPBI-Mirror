
package com.expedienteclinico.expedienteclinico.models.rrhh;
import com.expedienteclinico.expedienteclinico.models.StatusModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "rrhh_positions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PositionsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_position", nullable = false)
    private Long id;

    // CÓDIGO CORREGIDO
    @Column(name = "uuid", updatable = false, nullable = false, unique = true, length = 36)
    private String uuid = java.util.UUID.randomUUID().toString();

    @Column(length = 100, nullable = false)
    private String name;
    @Column(columnDefinition = "VARCHAR(MAX)", nullable = true)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estatus", nullable = false) //
    private StatusModel status;
}

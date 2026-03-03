package com.expedienteclinico.expedienteclinico.models.rrhh;
import com.expedienteclinico.expedienteclinico.models.StatusModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
//@Table(appliesTo = "Departments")
//@Table
@Table(name = "rrhh_departments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_department", nullable = false)
    private Long id;

    // CÓDIGO CORREGIDO
    @Column(name = "uuid", updatable = false, nullable = false, unique = true, length = 36)
    private String uuid = java.util.UUID.randomUUID().toString();

    @Column(length = 100, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estatus", nullable = false) //
    private StatusModel status;
}

package com.expedienteclinico.expedienteclinico.models.rrhh;

import com.expedienteclinico.expedienteclinico.models.StatusModel;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "rrhh_schedules")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SchedulesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schedule", nullable = false)
    private Long id;

    // CÓDIGO CORREGIDO
    @Column(name = "uuid", updatable = false, nullable = false, unique = true, length = 36)
    private String uuid = java.util.UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_employee", nullable = false)
    private EmployeesModel employee;

    @Column(name = "day_week", nullable = false, length = 15)
    private String dayOfWeek;

    @Column(name = "start_time", nullable = false)
    private String startTime;

    @Column(name = "end_time", nullable = false)
    private String endTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estatus", nullable = false) //
    private StatusModel status;
}
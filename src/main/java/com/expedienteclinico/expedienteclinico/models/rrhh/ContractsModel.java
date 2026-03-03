package com.expedienteclinico.expedienteclinico.models.rrhh;

import com.expedienteclinico.expedienteclinico.models.StatusModel;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "rrhh_contracts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContractsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contract", nullable = false)
    private Long id;

    // CÓDIGO CORREGIDO
    @Column(name = "uuid", updatable = false, nullable = false, unique = true, length = 36)
    private String uuid = java.util.UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_employee", nullable = false)
    private EmployeesModel employee;

    @Column(name = "contract_type", nullable = false, length = 50)
    private String contractType;

    @Column(name = "hiring_date", nullable = false)
    private String hiringDate;

    @Column(name = "termination_date")
    private String terminationDate;

    @Column(name = "base_salary", nullable = false, precision = 18, scale = 2)
    private BigDecimal baseSalary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estatus", nullable = false) //
    private StatusModel status;
}
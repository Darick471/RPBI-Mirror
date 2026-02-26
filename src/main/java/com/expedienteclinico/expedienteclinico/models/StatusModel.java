package com.expedienteclinico.expedienteclinico.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "status")
@Getter
@Setter
public class StatusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status", nullable = false) // Buena práctica: nombrar explícitamente la PK
    private Long id;

    @Column(name = "uuid", updatable = false, nullable = false, unique = true, length = 36)
    private String uuid = java.util.UUID.randomUUID().toString();

    @Column(name = "name", nullable = false, unique = true)
    private String statusName;
}
package com.expedienteclinico.expedienteclinico.seeders;

import com.expedienteclinico.expedienteclinico.models.StatusModel;
import com.expedienteclinico.expedienteclinico.models.rpbi.*;
import com.expedienteclinico.expedienteclinico.repositories.IStatusRepository;
import com.expedienteclinico.expedienteclinico.repositories.rpbi.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RpbiDataLoader implements CommandLineRunner {

    private final IStatusRepository statusRepository;
    private final IRpbiClasificationRepository classificationRepository;
    private final IRpbiPhysicalStateRepository physicalStateRepository;
    private final IRpbiContainerRepository containerRepository;
    private final IRpbiComplianceMatrixRepository matrixRepository;

    // Inyección de Variables de Entorno (Igual a la arquitectura de tu compañero)
    @Value("${STATUS1}")
    private String statusName;

    @Value("${RPBI_CLASIF_NAME}")
    private String clasifName;

    @Value("${RPBI_CLASIF_DESC}")
    private String clasifDesc;

    @Value("${RPBI_CLASIF_COLOR}")
    private String clasifColor;

    @Value("${RPBI_PHYS_STATE_NAME}")
    private String physStateName;

    @Value("${RPBI_PHYS_STATE_UNIT}")
    private String physStateUnit;

    @Value("${RPBI_CONT_NAME}")
    private String contName;

    @Value("${RPBI_CONT_DESC}")
    private String contDesc;

    // Constructor explícito para inyección de dependencias
    public RpbiDataLoader(IStatusRepository statusRepository,
                          IRpbiClasificationRepository classificationRepository,
                          IRpbiPhysicalStateRepository physicalStateRepository,
                          IRpbiContainerRepository containerRepository,
                          IRpbiComplianceMatrixRepository matrixRepository) {
        this.statusRepository = statusRepository;
        this.classificationRepository = classificationRepository;
        this.physicalStateRepository = physicalStateRepository;
        this.containerRepository = containerRepository;
        this.matrixRepository = matrixRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {

        // 1. Resolución Defensiva del Estatus
        StatusModel targetStatus;

        if (statusRepository.count() == 0) {
            // Si nuestro script corre primero, lo creamos con la variable de entorno
            StatusModel newStatus = new StatusModel();
            newStatus.setStatusName(statusName);
            targetStatus = statusRepository.save(newStatus);
        } else {
            // Si el de tu compañero corrió primero, usamos el suyo
            targetStatus = statusRepository.findById(1L)
                    .orElseThrow(() -> new IllegalStateException("Fallo de integridad: La tabla de estatus no está vacía, pero el ID 1 no existe."));
        }

        // 2. Sembrado Aislado del Módulo RPBI con variables de entorno
        if (classificationRepository.count() == 0) {

            RpbiClasificationModel clasifPunzo = new RpbiClasificationModel();
            clasifPunzo.setName(clasifName);
            clasifPunzo.setDescription(clasifDesc);
            clasifPunzo.setColorCode(clasifColor);
            clasifPunzo.setStatus(targetStatus);
            clasifPunzo = classificationRepository.save(clasifPunzo);

            RpbiPhysicalStateModel stateSolid = new RpbiPhysicalStateModel();
            stateSolid.setName(physStateName);
            stateSolid.setMeasureUnit(physStateUnit);
            stateSolid.setStatus(targetStatus);
            stateSolid = physicalStateRepository.save(stateSolid);

            RpbiContainerModel containerRigid = new RpbiContainerModel();
            containerRigid.setName(contName);
            containerRigid.setDescription(contDesc);
            containerRigid.setStatus(targetStatus);
            containerRigid = containerRepository.save(containerRigid);

            RpbiComplianceMatrixModel matrixRule = new RpbiComplianceMatrixModel();
            matrixRule.setClassification(clasifPunzo);
            matrixRule.setPhysicalState(stateSolid);
            matrixRule.setContainer(containerRigid);
            matrixRule.setStatus(targetStatus);
            matrixRepository.save(matrixRule);

            System.out.println("Sembrado defensivo completado: Catálogos RPBI insertados usando Variables de Entorno.");
        }
    }
}
package com.expedienteclinico.expedienteclinico.services.rpbi;

import com.expedienteclinico.expedienteclinico.beans.rpbi.RpbiGenerationRecordRequestObject;
import com.expedienteclinico.expedienteclinico.models.StatusModel;
import com.expedienteclinico.expedienteclinico.models.rpbi.*;
import com.expedienteclinico.expedienteclinico.repositories.rpbi.*;
import com.expedienteclinico.expedienteclinico.repositories.IStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RpbiGenerationRecordService {

    // Inyección inmutable por constructor (Seguridad y Testing)
    private final IRpbiGenerationRecordRepository generationRepository;
    private final IRpbiClasificationRepository classificationRepository;
    private final IRpbiPhysicalStateRepository physicalStateRepository;
    private final IRpbiContainerRepository containerRepository;
    private final IRpbiComplianceMatrixRepository complianceMatrixRepository;
    private final IStatusRepository statusRepository;

    @Transactional
    public RpbiGenerationRecordModel createRecord(RpbiGenerationRecordRequestObject request) {

        // 1. Traducción de UUID públicos a Entidades de BD
        RpbiClasificationModel classification = classificationRepository.findByUuid(request.getClassificationUuid())
                .orElseThrow(() -> new IllegalArgumentException("Classification UUID no válido o no encontrado"));

        RpbiPhysicalStateModel physicalState = physicalStateRepository.findByUuid(request.getPhysicalStateUuid())
                .orElseThrow(() -> new IllegalArgumentException("Physical State UUID no válido o no encontrado"));

        RpbiContainerModel container = containerRepository.findByUuid(request.getContainerUuid())
                .orElseThrow(() -> new IllegalArgumentException("Container UUID no válido o no encontrado"));


        boolean isNom087Compliant = complianceMatrixRepository.existsByClassificationAndPhysicalStateAndContainerAndStatus_Id(
                classification, physicalState, container, 1L);

        if (!isNom087Compliant) {
            // El cruce de estos 3 catálogos no existe en la matriz o está inactivo.
            throw new IllegalStateException("Violación a la NOM-087: La combinación solicitada no está permitida en la matriz normativa actual.");
        }

        // 3. Mapeo del registro transaccional
        RpbiGenerationRecordModel record = new RpbiGenerationRecordModel();
        record.setClassification(classification);
        record.setPhysicalState(physicalState);
        record.setContainer(container);
        record.setQuantity(request.getQuantity());
        record.setGenerationArea(request.getGenerationArea());
        record.setResponsibleUser(request.getResponsibleUser());
        record.setGenerationDate(LocalDateTime.now());

        // Asignación de estatus activo para el nuevo registro transaccional
        StatusModel activeStatus = statusRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Estatus base no encontrado"));
        record.setStatus(activeStatus);

        record.setGenerationDate(LocalDateTime.now()); //

        // 4. Persistencia
        return generationRepository.save(record);
    }
}
package com.expedienteclinico.expedienteclinico.controllers.rpbi;

import com.expedienteclinico.expedienteclinico.beans.rpbi.RpbiGenerationRecordRequestObject;
import com.expedienteclinico.expedienteclinico.beans.rpbi.RpbiGenerationRecordResponseObject;
import com.expedienteclinico.expedienteclinico.models.rpbi.RpbiGenerationRecordModel;
import com.expedienteclinico.expedienteclinico.payload.response.ResponseFactory;
import com.expedienteclinico.expedienteclinico.services.rpbi.RpbiGenerationRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/rpbi/records")
@RequiredArgsConstructor
public class RpbiGenerationRecordController {

    private final RpbiGenerationRecordService service;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createRecord(
            @Valid @RequestBody RpbiGenerationRecordRequestObject request,
            BindingResult result) {

        // 1. Interceptar errores del DTO de entrada (Si faltan UUIDs o texto en blanco)
        if (result.hasErrors()) {
            return new ResponseEntity<>(ResponseFactory.getErrorResponse(result), HttpStatus.BAD_REQUEST);
        }

        try {
            // 2. Ejecutar la lógica de negocio (Matriz NOM-087)
            RpbiGenerationRecordModel savedModel = service.createRecord(request);

            // 3. Mapear el Modelo de BD al DTO de Salida Seguro
            RpbiGenerationRecordResponseObject responseObj = new RpbiGenerationRecordResponseObject();
            responseObj.setRecordUuid(savedModel.getUuid());
            responseObj.setClassificationName(savedModel.getClassification().getName());
            responseObj.setPhysicalStateName(savedModel.getPhysicalState().getName());
            responseObj.setContainerName(savedModel.getContainer().getName());
            responseObj.setQuantity(savedModel.getQuantity());
            responseObj.setGenerationArea(savedModel.getGenerationArea());
            responseObj.setResponsibleUser(savedModel.getResponsibleUser());
            responseObj.setGenerationDate(savedModel.getGenerationDate());
            responseObj.setStatusName(savedModel.getStatus().getStatusName());

            return new ResponseEntity<>(
                    ResponseFactory.getCreatedResponse("Registro de RPBI generado exitosamente.", responseObj),
                    HttpStatus.CREATED
            );

        } catch (IllegalArgumentException | IllegalStateException e) {
            // 4. Capturar las violaciones de la NOM-087 o UUIDs falsos que lanza el Servicio
            Map<String, Object> errorBody = new java.util.LinkedHashMap<>();
            errorBody.put("message", e.getMessage());
            return new ResponseEntity<>(errorBody, HttpStatus.CONFLICT); // 409 Conflict es ideal para reglas de negocio rotas
        } catch (Exception e) {
            // 5. Captura general de fallos del servidor
            Map<String, Object> errorBody = new java.util.LinkedHashMap<>();
            errorBody.put("message", "Error interno al procesar el registro.");
            errorBody.put("error", e.getMessage());
            return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
package com.expedienteclinico.expedienteclinico.services.rpbi;

import com.expedienteclinico.expedienteclinico.beans.rpbi.RpbiClasificationObject; // Asegúrate de crear este DTO como vimos antes
import com.expedienteclinico.expedienteclinico.models.rpbi.RpbiClasificationModel;
import com.expedienteclinico.expedienteclinico.repositories.rpbi.IRpbiClasificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RpbiClasificationService {

    private final IRpbiClasificationRepository repository;

    // Método para obtener todo el catálogo convertido a DTO
    public List<RpbiClasificationObject> getAllClasificaciones() {
        // buscamos la data en crudo de la BD
        List<RpbiClasificationModel> entidades = repository.findAll();

        // preparamos la lista de respuesta
        List<RpbiClasificationObject> dtos = new ArrayList<>();

        // ciclo de mapeo
        for (RpbiClasificationModel entidad : entidades) {
            RpbiClasificationObject dto = new RpbiClasificationObject();

            // mapeo 1 a 1
            dto.setId(entidad.getId());
            dto.setNombre(entidad.getName());
            dto.setDescripcion(entidad.getDescription());
            dto.setCodigoColor(entidad.getColorCode());

            // mapeo relacional
            if (entidad.getStatus() != null) {
                // se separa el id y nombre, segun los campos declarados en el Dto
                dto.setEstatusId(entidad.getStatus().getId());
                dto.setEstatusNombre(entidad.getStatus().getStatusName());
            }

            dtos.add(dto);
        }

        return dtos;
    }
}
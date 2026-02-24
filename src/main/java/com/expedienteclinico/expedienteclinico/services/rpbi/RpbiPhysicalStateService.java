package com.expedienteclinico.expedienteclinico.services.rpbi;

import com.expedienteclinico.expedienteclinico.beans.rpbi.RpbiPhysicalStateObject;
import com.expedienteclinico.expedienteclinico.repositories.rpbi.IRpbiPhysicalStateRepository;
import com.expedienteclinico.expedienteclinico.models.rpbi.RpbiPhysicalStateModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RpbiPhysicalStateService {

    private final IRpbiPhysicalStateRepository repository;

    public List<RpbiPhysicalStateObject> getAll(){

        List<RpbiPhysicalStateModel> entidades = repository.findAll();

        List<RpbiPhysicalStateObject> dtos = new ArrayList<>();

        for (RpbiPhysicalStateModel entidad : entidades){

            RpbiPhysicalStateObject dto = new RpbiPhysicalStateObject();

            dto.setId(entidad.getId());
            dto.setNombre(entidad.getName());
            dto.setUnidadMedida(entidad.getMeasureUnit());

            if(entidad.getStatus() != null ){

                dto.setEstatusId(entidad.getStatus().getId());
                dto.setEstatusNombre(entidad.getStatus().getStatusName());
            }
            dtos.add(dto);
        }

        return dtos;
    }

}

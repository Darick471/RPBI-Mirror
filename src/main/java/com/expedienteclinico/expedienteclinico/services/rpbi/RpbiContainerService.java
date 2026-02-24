package com.expedienteclinico.expedienteclinico.services.rpbi;

import com.expedienteclinico.expedienteclinico.models.rpbi.RpbiContainerModel;
import com.expedienteclinico.expedienteclinico.repositories.rpbi.IRpbiContainerRepository;
import com.expedienteclinico.expedienteclinico.beans.rpbi.RpbiContainerObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RpbiContainerService {

    private final IRpbiContainerRepository repository;

public List<RpbiContainerObject> getAll(){

    List<RpbiContainerModel> entidades = repository.findAll();
    List<RpbiContainerObject> dtos = new ArrayList<>();

    for (RpbiContainerModel entidad : entidades){

        RpbiContainerObject dto = new RpbiContainerObject();

        dto.setUuid(entidad.getUuid());
        dto.setNombre(entidad.getName());
        dto.setDescripcion(entidad.getDescription());
        if(entidad.getStatus() != null ){

            dto.setStatusUuid(entidad.getStatus().getUuid());
            dto.setEstatusNombre(entidad.getStatus().getStatusName());
        }
        dtos.add(dto);
    }
return dtos;
}

}

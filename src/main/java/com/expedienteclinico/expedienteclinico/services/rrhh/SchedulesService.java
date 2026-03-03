package com.expedienteclinico.expedienteclinico.services.rrhh;

import com.expedienteclinico.expedienteclinico.models.rrhh.SchedulesModel;
import com.expedienteclinico.expedienteclinico.repositories.rrhh.ISchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulesService {
    @Autowired
    ISchedulesRepository schedulesRepository;

    public List<SchedulesModel> getAll() {
        return schedulesRepository.findAll();
    }


}

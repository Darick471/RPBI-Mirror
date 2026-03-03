package com.expedienteclinico.expedienteclinico.services.rrhh;

import com.expedienteclinico.expedienteclinico.models.rrhh.ClinicDetailsModel;
import com.expedienteclinico.expedienteclinico.repositories.rrhh.IClinicDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicDetailsService {
    @Autowired
    IClinicDetailsRepository clinicDetailsRepository;

    public List<ClinicDetailsModel> getAll() {
        return clinicDetailsRepository.findAll();
    }

}

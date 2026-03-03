package com.expedienteclinico.expedienteclinico.services.rrhh;

import com.expedienteclinico.expedienteclinico.models.rrhh.ContractsModel;
import com.expedienteclinico.expedienteclinico.repositories.rrhh.IContractsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractsService {
    @Autowired
    IContractsRepository contractsRepository;

    public List<ContractsModel> getAll() {
        return contractsRepository.findAll();
    }


}

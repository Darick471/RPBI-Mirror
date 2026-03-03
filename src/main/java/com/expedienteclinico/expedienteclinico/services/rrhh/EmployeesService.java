package com.expedienteclinico.expedienteclinico.services.rrhh;

import com.expedienteclinico.expedienteclinico.models.rrhh.DepartmentsModel;
import com.expedienteclinico.expedienteclinico.models.rrhh.EmployeesModel;
import com.expedienteclinico.expedienteclinico.repositories.rrhh.IDepartmentsRepository;
import com.expedienteclinico.expedienteclinico.repositories.rrhh.IEmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesService {
    @Autowired
    IEmployeesRepository employeesRepository;

    public List<EmployeesModel> getAll() {
        return employeesRepository.findAll();
    }


}

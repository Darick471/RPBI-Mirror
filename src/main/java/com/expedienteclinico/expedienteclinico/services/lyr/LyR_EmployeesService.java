package com.expedienteclinico.expedienteclinico.services.lyr;


import com.expedienteclinico.expedienteclinico.beans.lyr.LyR_EmployeesObject;
import com.expedienteclinico.expedienteclinico.models.lyr.LyR_EmployeesModel;
import com.expedienteclinico.expedienteclinico.repositories.lyr.ILyR_EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LyR_EmployeesService {
    @Autowired
    private ILyR_EmployeesRepository iLyR_employeesRepository;


    public List<LyR_EmployeesObject> getAll() {


        List<LyR_EmployeesModel> empleados = iLyR_employeesRepository.findAll();
        List<LyR_EmployeesObject> dts = new ArrayList<>();

        for (LyR_EmployeesModel empleado : empleados) {

            LyR_EmployeesObject emp = new LyR_EmployeesObject();


            emp.setId_empleado(empleado.getId_empleado());
            emp.setNombre(empleado.getNombre());
            emp.setApellido(empleado.getApellido());
            emp.setTelefono(empleado.getTelefono());
            emp.setArea(empleado.getArea());
            emp.setTurno(empleado.getTurno());
            if (empleado.getStatus() != null) {
                emp.setStatusId(empleado.getStatus().getId());
                emp.setStatusName(empleado.getStatus().getStatusName());
            }
            dts.add(emp);
        }
        return dts;
    }
}

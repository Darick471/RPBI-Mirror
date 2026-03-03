package com.expedienteclinico.expedienteclinico.controllers.rrhh;

import com.expedienteclinico.expedienteclinico.models.rrhh.EmployeesModel;
import com.expedienteclinico.expedienteclinico.services.rrhh.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rrhh/employees/")
public class EmployeesController {
    @Autowired
    EmployeesService employeesService;

    @GetMapping("get")
    public List<EmployeesModel> getPos(){
        return employeesService.getAll();
    }

}

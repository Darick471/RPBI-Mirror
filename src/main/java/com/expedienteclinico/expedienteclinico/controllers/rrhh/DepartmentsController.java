package com.expedienteclinico.expedienteclinico.controllers.rrhh;
import com.expedienteclinico.expedienteclinico.models.rrhh.DepartmentsModel;
import com.expedienteclinico.expedienteclinico.services.rrhh.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rrhh/departments/")
@CrossOrigin(origins = "*")
public class DepartmentsController {
    @Autowired
    DepartmentsService departmentService;

    @GetMapping("get")
    public List<DepartmentsModel> getAll() {
        return departmentService.getAll();
    }
}

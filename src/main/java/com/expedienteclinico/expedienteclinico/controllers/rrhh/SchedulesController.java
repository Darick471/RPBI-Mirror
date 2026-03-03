package com.expedienteclinico.expedienteclinico.controllers.rrhh;

import com.expedienteclinico.expedienteclinico.models.rrhh.SchedulesModel;
import com.expedienteclinico.expedienteclinico.services.rrhh.SchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rrhh/schedules/")
public class SchedulesController {
    @Autowired
    SchedulesService schedulesService;

    @GetMapping("get")
    public List<SchedulesModel> getAll(){
        return schedulesService.getAll();
    }

}

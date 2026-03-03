package com.expedienteclinico.expedienteclinico.controllers.rrhh;

import com.expedienteclinico.expedienteclinico.models.rrhh.ClinicDetailsModel;
import com.expedienteclinico.expedienteclinico.services.rrhh.ClinicDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rrhh/clinic-details/")
public class ClinicDetailsController {
    @Autowired
    ClinicDetailsService clinicDetailsService;

    @GetMapping("get")
    public List<ClinicDetailsModel> getAll(){
        return clinicDetailsService.getAll();
    }

}

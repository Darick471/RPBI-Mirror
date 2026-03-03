package com.expedienteclinico.expedienteclinico.controllers.lyr;

import com.expedienteclinico.expedienteclinico.beans.lyr.Cleaning_suppliesObject;
import com.expedienteclinico.expedienteclinico.beans.lyr.LyR_EmployeesObject;
import com.expedienteclinico.expedienteclinico.models.lyr.LyR_EmployeesModel;
import com.expedienteclinico.expedienteclinico.payload.response.ResponseFactory;
import com.expedienteclinico.expedienteclinico.services.lyr.LyR_EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin( origins = "*" , maxAge = 3600 )
@RestController
@RequestMapping( "/empleados_LyR-controller/" )
public class LyR_EmployeesController {

    @Autowired
    private LyR_EmployeesService LyR_EmployeesService;

    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> getAll() {

        List<LyR_EmployeesObject> empledos = LyR_EmployeesService.getAll();
        return new ResponseEntity<Map<String, Object>>(ResponseFactory.getSuccessOnGetAllResponse(empledos), HttpStatus.OK);
    }
}

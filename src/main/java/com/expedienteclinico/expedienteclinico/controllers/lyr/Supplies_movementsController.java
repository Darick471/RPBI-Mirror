package com.expedienteclinico.expedienteclinico.controllers.lyr;


import com.expedienteclinico.expedienteclinico.beans.lyr.LyR_EmployeesObject;
import com.expedienteclinico.expedienteclinico.beans.lyr.Supplies_movementsObject;
import com.expedienteclinico.expedienteclinico.models.lyr.Supplies_movementsModel;
import com.expedienteclinico.expedienteclinico.payload.response.ResponseFactory;
import com.expedienteclinico.expedienteclinico.services.lyr.Supplies_movementsService;
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
@RequestMapping( "/movimiento_insumos-controller/" )
public class Supplies_movementsController {

    @Autowired
    Supplies_movementsService supplies_movementsService;

    @GetMapping( "all")
    public ResponseEntity<Map<String, Object>> getAll() {

        List<Supplies_movementsObject> empledos = supplies_movementsService.getAll();
        return new ResponseEntity<Map<String, Object>>(ResponseFactory.getSuccessOnGetAllResponse(empledos), HttpStatus.OK);
    }
}

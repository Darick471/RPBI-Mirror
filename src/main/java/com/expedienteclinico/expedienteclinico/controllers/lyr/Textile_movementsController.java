package com.expedienteclinico.expedienteclinico.controllers.lyr;


import com.expedienteclinico.expedienteclinico.beans.lyr.Cleaning_suppliesObject;
import com.expedienteclinico.expedienteclinico.beans.lyr.Textile_movementsObject;
import com.expedienteclinico.expedienteclinico.models.lyr.Textile_movementsModel;
import com.expedienteclinico.expedienteclinico.payload.response.ResponseFactory;
import com.expedienteclinico.expedienteclinico.services.lyr.Textile_movementsService;
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
@RequestMapping( "/movimiento_textiles-controller/" )
public class Textile_movementsController {

    @Autowired
    private Textile_movementsService textile_movementsService;

    @GetMapping( "all")
    public ResponseEntity<Map<String, Object>> getAll() {

        List<Textile_movementsObject> textile = textile_movementsService.getAll();
        return new ResponseEntity<Map<String, Object>>( ResponseFactory.getSuccessOnGetAllResponse(textile) , HttpStatus.OK );
    }
}

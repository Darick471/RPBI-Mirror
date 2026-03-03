package com.expedienteclinico.expedienteclinico.controllers.lyr;

import com.expedienteclinico.expedienteclinico.beans.lyr.Cleaning_suppliesObject;
import com.expedienteclinico.expedienteclinico.payload.response.ResponseFactory;
import com.expedienteclinico.expedienteclinico.services.lyr.Cleaning_suppliesService;
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
@RequestMapping( "/cleaning_supplies-controller/" )
public class Cleaning_suppliesController {

    @Autowired
    private Cleaning_suppliesService Cleaning_suppliesService;

    @GetMapping( "all")
    public ResponseEntity<Map<String, Object>> getAll() {

        List<Cleaning_suppliesObject> supplies = Cleaning_suppliesService.getAll();
        return new ResponseEntity<Map<String, Object>>( ResponseFactory.getSuccessOnGetAllResponse(supplies) , HttpStatus.OK );
    }
}

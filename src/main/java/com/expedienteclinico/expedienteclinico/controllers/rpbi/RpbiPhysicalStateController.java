package com.expedienteclinico.expedienteclinico.controllers.rpbi;

import com.expedienteclinico.expedienteclinico.beans.rpbi.RpbiPhysicalStateObject;
import com.expedienteclinico.expedienteclinico.payload.response.ResponseFactory;
import com.expedienteclinico.expedienteclinico.services.rpbi.RpbiPhysicalStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

// @CrossOrigin( origins = "*" , maxAge = 3600 )
@RestController
@RequestMapping("/api/rpbi/physicalstates")
public class RpbiPhysicalStateController {

    @Autowired
    private RpbiPhysicalStateService service;
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll() {

        List<RpbiPhysicalStateObject> lista = service.getAll();

        return new  ResponseEntity<Map<String, Object>>( ResponseFactory.getSuccessOnGetAllResponse(lista) , HttpStatus.OK );

    }

}
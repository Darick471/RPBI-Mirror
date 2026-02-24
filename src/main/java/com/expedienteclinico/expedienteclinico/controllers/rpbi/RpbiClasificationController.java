package com.expedienteclinico.expedienteclinico.controllers.rpbi;

import com.expedienteclinico.expedienteclinico.beans.rpbi.RpbiClasificationObject;
import com.expedienteclinico.expedienteclinico.payload.response.ResponseFactory;
import com.expedienteclinico.expedienteclinico.services.rpbi.RpbiClasificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rpbi/clasifications") // ruta base del módulo
// @CrossOrigin( origins = "*" , maxAge = 3600 ) // Para pruebas (?) xd
public class RpbiClasificationController {

    @Autowired
    private RpbiClasificationService service;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll() {

        List<RpbiClasificationObject> lista = service.getAllClasificaciones();
        return new  ResponseEntity<Map<String, Object>>( ResponseFactory.getSuccessOnGetAllResponse(lista) , HttpStatus.OK );


    }
}
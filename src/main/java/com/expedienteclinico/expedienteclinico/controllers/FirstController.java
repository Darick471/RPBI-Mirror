package com.expedienteclinico.expedienteclinico.controllers;

import com.expedienteclinico.expedienteclinico.beans.FirstObject;
import com.expedienteclinico.expedienteclinico.models.FirstModel;
import com.expedienteclinico.expedienteclinico.services.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Deprecated
@CrossOrigin( origins = "*" , maxAge = 3600 )
@RestController
@RequestMapping( "/first-controller/" )
public class FirstController {

    @Autowired
    FirstService firstService ;

    @GetMapping( "first-single" )
    public FirstObject getFirstSingle() {
        FirstObject a = new FirstObject() ;
            a.setNombre( "Juanito" ) ;
            a.setDireccion( "Villas Jotoch" ) ;
            a.setEdad( 21 ) ;
            a.setTelefono( "9999999999" ) ;

        return a ;
    }

    @GetMapping( "first-list" )
    public ArrayList< Object > getFirstList() {
        return firstService.getFirstList() ;
    }

    @PostMapping( "new" )
    public Object nuevo( @RequestBody FirstObject firstObject ) {
        return firstService.nuevo( firstObject ) ;
    }




    @GetMapping( "fibonacci" )
    public void fibonacci() {

        System.out.println("Serie de Fibonacci" ) ;
        firstService.fibonacci(50);

    }


    @GetMapping( "all" )
    public List< FirstModel > getAll() {
        return firstService.getAll() ;
    }


    @PutMapping( "change/{id}" )
    public ResponseEntity< ? > updateData(@Valid @RequestBody FirstModel firstModel , BindingResult result , @PathVariable Long id){

        return firstService.updateData(firstModel, result, id);

    }


    }

package com.expedienteclinico.expedienteclinico.services;

import com.expedienteclinico.expedienteclinico.beans.FirstObject;
import com.expedienteclinico.expedienteclinico.models.FirstModel;
import com.expedienteclinico.expedienteclinico.payload.response.ResponseFactory;
import com.expedienteclinico.expedienteclinico.repositories.IFirstRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Deprecated
@Service
public class FirstService {

    @Autowired
    IFirstRepository iFirstRepository ;

    public ArrayList<Object> getFirstList() {
        ArrayList< Object > myObjectOfObjects = new ArrayList<>() ;

        FirstObject a = new FirstObject() ;
            a.setNombre( "Pablito" ) ;
            a.setDireccion( "Villas Jotoch" ) ;
            a.setEdad( 21 ) ;
            a.setTelefono( "9999999999" ) ;

        FirstObject b = new FirstObject() ;
            b.setNombre( "Panchito" ) ;
            b.setDireccion( "Villas gaymar" ) ;
            b.setEdad( 20 ) ;
            b.setTelefono( "8888888888" ) ;

        FirstObject c = new FirstObject() ;
            c.setNombre( "Joseito" ) ;
            c.setDireccion( "Rancho Viejochos" ) ;
            c.setEdad( 22 ) ;
            c.setTelefono( "7777777777" ) ;

        myObjectOfObjects.add( a ) ;
        myObjectOfObjects.add( b ) ;
        myObjectOfObjects.add( c ) ;

        return myObjectOfObjects ;
    }


    public Object nuevo( FirstObject firstObject ) {

        ArrayList< Object > a = new ArrayList<>() ;
            a.add("draw: 1");
            a.add("recordsTotal: 57");
            a.add("recordsFiltered: 57");
            a.add("data:");
            a.add( firstObject ) ;

        return a ;
    }

    public void fibonacci( int MAX ) {

        int firstNumber = 0 ;
        int secondNumber = 1 ;
        int fibonacci = '\0' ;

        System.out.print( firstNumber + " " ) ;
        System.out.print( secondNumber + " " ) ;

        for ( int i = 2 ; i < MAX ; i++ ) {

            fibonacci = firstNumber + secondNumber ;
            System.out.print( fibonacci + " \n" ) ;

            firstNumber = secondNumber ;
            secondNumber = fibonacci ;
        }
    }


    public List< FirstModel > getAll() {
        return iFirstRepository.findAll() ;
    }

    public ResponseEntity< Map< String , Object > > updateData(FirstModel firstModel, BindingResult result, Long id) {

        FirstModel ModelToChange = null;

        if( result.hasErrors() ) return new ResponseEntity< Map< String, Object> >( ResponseFactory.getErrorResponse( result ), HttpStatus.BAD_REQUEST );

        try {
            ModelToChange = iFirstRepository.findById( id ).orElse(null );

            if ( ModelToChange == null ) return new  ResponseEntity<Map< String, Object> >( ResponseFactory.getNotFoundResponse( ModelToChange ) , HttpStatus.NOT_FOUND );

            ModelToChange.setNombre( firstModel.getNombre() );
            ModelToChange.setApellido(firstModel.getApellido());

            iFirstRepository.save( ModelToChange );
        } catch ( DataAccessException e ) {

            return new  ResponseEntity<Map<String, Object>>( ResponseFactory.getErrorToUpdateResponse(firstModel) , HttpStatus.INTERNAL_SERVER_ERROR );

        }

        return new  ResponseEntity<Map<String, Object>>( ResponseFactory.getUpdateResponse(ModelToChange) , HttpStatus.CREATED );

    }
}

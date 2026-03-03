package com.expedienteclinico.expedienteclinico.controllers.Patients;

import com.expedienteclinico.expedienteclinico.beans.Patients.ClinicHistoryBean;
import com.expedienteclinico.expedienteclinico.models.patients.ClinicHistoryModel;
import com.expedienteclinico.expedienteclinico.services.Patients.ClinicHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin( origins ="http://localhost:3000", maxAge = 3600 )
@RestController
@RequestMapping( "/clinichistory-controller/" )
public class ClinicHistoryController {

    @Autowired
    ClinicHistoryService clinicHistoryService ;

    @GetMapping( "clinichistory-single" )
    public ClinicHistoryBean getClinicHistorySingle() {
        ClinicHistoryBean a = new ClinicHistoryBean() ;
        a.setMotivoConsulta( "Dolor de cabeza severo" ) ;
        a.setDiagnosticoPreliminar( "Migraña" ) ;
        return a ;
    }

    @GetMapping( "clinichistory-list" )
    public List<ClinicHistoryBean> getClinicHistoryList() { // <-- Cambiado a List
        return clinicHistoryService.getAllClinicHistory() ;
    }

    @PostMapping( "new" )
    public Object nuevo( @Valid @RequestBody ClinicHistoryBean clinicHistoryBean ) {
        return clinicHistoryService.nuevo( clinicHistoryBean ) ;
    }

    @GetMapping( "all" )
    public List<ClinicHistoryModel> getAll() {
        return clinicHistoryService.getAll() ;
    }

    @PutMapping ( "change/{id}" )
    public ResponseEntity < ? > updateData (@Valid @RequestBody ClinicHistoryModel clinicHistoryModel , BindingResult result, @PathVariable UUID id ) {
        return clinicHistoryService.updateData(clinicHistoryModel, result, id);
    }
}
package com.expedienteclinico.expedienteclinico.controllers.morgue;

import com.expedienteclinico.expedienteclinico.beans.morgue.MorgueObject;
import com.expedienteclinico.expedienteclinico.models.morgue.Morgue;
import com.expedienteclinico.expedienteclinico.services.morgue.MorgueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/morgue-controller/")
public class MorgueController {

    @Autowired
    MorgueService morgueService;

    @GetMapping("morgue-list")
    public ArrayList<Object> getMorgueList() {
        return morgueService.getMorgueList();
    }

    @GetMapping("all")
    public List<Morgue> getAll() {
        return morgueService.getAll();
    }

    // /morgue-controller/buscar-paciente/id de coffe
    @GetMapping("buscar-paciente/{id}")
    public Morgue getByPaciente(@PathVariable Long id) {
        // pide al servicio que busque al fallecido vinculado a ese ID de paciente
        return morgueService.buscarPorPaciente(id);
    }
    //prueba mck
    @GetMapping("buscar-paciente-local/{id}")
    public Morgue buscarLocal(@PathVariable Long id) {
        return morgueService.buscarPorPacienteLocal(id);
    }
}
package com.expedienteclinico.expedienteclinico.controllers.rrhh;

import com.expedienteclinico.expedienteclinico.models.rrhh.PositionsModel;
import com.expedienteclinico.expedienteclinico.services.rrhh.PositionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rrhh/positions/")
public class PositionsController {
    @Autowired
    PositionsService positionsService;

    @GetMapping("get")
    public List<PositionsModel> getPos(){
        return positionsService.getPos();
    }

}

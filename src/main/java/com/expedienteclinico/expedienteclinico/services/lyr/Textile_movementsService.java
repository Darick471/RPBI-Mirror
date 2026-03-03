package com.expedienteclinico.expedienteclinico.services.lyr;


import com.expedienteclinico.expedienteclinico.beans.lyr.Cleaning_suppliesObject;
import com.expedienteclinico.expedienteclinico.beans.lyr.LyR_EmployeesObject;
import com.expedienteclinico.expedienteclinico.beans.lyr.Textile_movementsObject;
import com.expedienteclinico.expedienteclinico.models.lyr.Cleaning_suppliesModel;
import com.expedienteclinico.expedienteclinico.models.lyr.Textile_movementsModel;
import com.expedienteclinico.expedienteclinico.repositories.lyr.ITextile_movementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class Textile_movementsService {

    @Autowired
    private ITextile_movementsRepository iTextile_movementsRepository;

    public List<Textile_movementsObject> getAll() {


        List<Textile_movementsModel> textiles= iTextile_movementsRepository.findAll();
        List<Textile_movementsObject> dts = new ArrayList<>();

        for (Textile_movementsModel textile : textiles) {

            Textile_movementsObject dtos = new Textile_movementsObject();

            dtos.setId_motion(textile.getId_motion());
            dtos.setType_motion(textile.getType_motion());
            dtos.setAmount(textile.getAmount());
            dtos.setMotion_date(textile.getMotion_date());
            dtos.setObservations(textile.getObservations());
            if (textile.getTextile() != null) {
                dtos.setArticuloId(textile.getTextile().getId_articles());
            }
            if (textile.getEmpleado() != null) {
                dtos.setEmployeesNombre(textile.getEmpleado().getNombre());
            }
            dts.add(dtos);
        }
        return dts;
    }
}

package com.expedienteclinico.expedienteclinico.services.lyr;


import com.expedienteclinico.expedienteclinico.beans.lyr.Supplies_movementsObject;
import com.expedienteclinico.expedienteclinico.models.lyr.Supplies_movementsModel;
import com.expedienteclinico.expedienteclinico.repositories.lyr.Supplies_movementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Supplies_movementsService {

    @Autowired
    private Supplies_movementsRepository supplies_movementsRepository;

    public List<Supplies_movementsObject> getAll() {


        List<Supplies_movementsModel> suppliesM = supplies_movementsRepository.findAll();
        List<Supplies_movementsObject> dts = new ArrayList<>();

        for (Supplies_movementsModel supplieM : suppliesM) {

            Supplies_movementsObject dtos = new Supplies_movementsObject();

            dtos.setId_motion(supplieM.getId_motion());
            dtos.setType_motion(supplieM.getType_motion());
            dtos.setAmount(supplieM.getAmount());
            dtos.setMotion_date(supplieM.getMotion_date());
            dtos.setObservations(supplieM.getObservations());
            if (supplieM.getSupplies() != null) {
                dtos.setSuppliesId(supplieM.getSupplies().getId_supplies());
            }
            if (supplieM.getEmployees() != null) {
                dtos.setEmployeesName(supplieM.getEmployees().getNombre());
            }

        }
        return dts;
    }
}

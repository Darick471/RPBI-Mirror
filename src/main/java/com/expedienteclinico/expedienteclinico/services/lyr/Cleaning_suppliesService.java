package com.expedienteclinico.expedienteclinico.services.lyr;



import com.expedienteclinico.expedienteclinico.beans.lyr.Cleaning_suppliesObject;
import com.expedienteclinico.expedienteclinico.models.lyr.Cleaning_suppliesModel;
import com.expedienteclinico.expedienteclinico.repositories.lyr.ICleaning_suppliesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Cleaning_suppliesService {

    @Autowired
    private ICleaning_suppliesRepository iCleaning_suppliesRepository;

    public List<com.expedienteclinico.expedienteclinico.beans.lyr.Cleaning_suppliesObject> getAll() {


        List<Cleaning_suppliesModel> supplies = iCleaning_suppliesRepository.findAll() ;
        List<Cleaning_suppliesObject> dts = new ArrayList<>();

        for (Cleaning_suppliesModel supplie : supplies) {

            com.expedienteclinico.expedienteclinico.beans.lyr.Cleaning_suppliesObject dtos = new com.expedienteclinico.expedienteclinico.beans.lyr.Cleaning_suppliesObject();

            dtos.setId_supplies(supplie.getId_supplies());
            dtos.setName(supplie.getName());
            dtos.setUnit_measurement(supplie.getUnit_measurement());
            dtos.setCurrent_stock(supplie.getCurrent_stock());
            dtos.setStock_min(supplie.getStock_min());
            dtos.setExpiration_date(supplie.getExpiration_date());
           if (supplie.getStatus() != null) {
                dtos.setStatusId(supplie.getStatus().getId());
                dtos.setStatusNombre(supplie.getStatus().getStatusName());
            }
            dts.add(dtos);
        }
        return dts;
    }
}

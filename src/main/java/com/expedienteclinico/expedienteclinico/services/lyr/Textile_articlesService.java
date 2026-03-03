package com.expedienteclinico.expedienteclinico.services.lyr;


import com.expedienteclinico.expedienteclinico.beans.lyr.Cleaning_suppliesObject;
import com.expedienteclinico.expedienteclinico.beans.lyr.Textile_articlesObject;
import com.expedienteclinico.expedienteclinico.models.lyr.Cleaning_suppliesModel;
import com.expedienteclinico.expedienteclinico.models.lyr.Textile_articlesModel;
import com.expedienteclinico.expedienteclinico.repositories.lyr.Textile_articlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Textile_articlesService {

    @Autowired
    private Textile_articlesRepository itextile_articlesRepository;

    public List<Textile_articlesObject> getAll() {


        List<Textile_articlesModel> articles = itextile_articlesRepository.findAll();
        List<Textile_articlesObject> dts = new ArrayList<>();

        for (Textile_articlesModel article : articles ) {

            Textile_articlesObject dtos = new Textile_articlesObject();

            dtos.setId_articles(article.getId_articles());
            dtos.setName(article.getName());
            dtos.setDescription(article.getDescripcion());
            if (article.getStatus() != null) {
                dtos.setStatusId(article.getStatus().getId());
                dtos.setStatusName(article.getStatus().getStatusName());
            }
            dts.add(dtos);
        }
        return dts;
    }
}

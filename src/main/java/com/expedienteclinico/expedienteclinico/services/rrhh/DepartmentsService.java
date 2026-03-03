package com.expedienteclinico.expedienteclinico.services.rrhh;
import com.expedienteclinico.expedienteclinico.models.rrhh.DepartmentsModel;
import com.expedienteclinico.expedienteclinico.repositories.rrhh.IDepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class DepartmentsService {
    @Autowired
    IDepartmentsRepository departmentsRepo;

    public List<DepartmentsModel> getAll() {
        return departmentsRepo.findAll();
    }

    //Metodo para guardar datos despues
    public DepartmentsModel saveInfo(DepartmentsModel depto) {
        return departmentsRepo.save(depto);
    }
}

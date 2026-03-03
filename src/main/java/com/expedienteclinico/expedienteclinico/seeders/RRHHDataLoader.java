package com.expedienteclinico.expedienteclinico.seeders;

import com.expedienteclinico.expedienteclinico.models.StatusModel;
import com.expedienteclinico.expedienteclinico.models.rrhh.DepartmentsModel;
import com.expedienteclinico.expedienteclinico.models.rrhh.PositionsModel;
import com.expedienteclinico.expedienteclinico.repositories.IStatusRepository;
import com.expedienteclinico.expedienteclinico.repositories.rrhh.IDepartmentsRepository;
import com.expedienteclinico.expedienteclinico.repositories.rrhh.IPositionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.UUID;



@Component
public class RRHHDataLoader implements CommandLineRunner {

    StatusModel Active;
    StatusModel Inactive;

    @Autowired
    private IStatusRepository Srepository;

    @Autowired
    private IDepartmentsRepository Drepository;

    @Autowired
    private IPositionsRepository Prepository;


    private void StaData() {
        // 1. Instanciamos vacío (Hibernate se encargará del ID y la clase generará el UUID)
        StatusModel statusActivo = new StatusModel();
        // 2. Seteamos únicamente el nombre
        statusActivo.setStatusName("Active");
        // 3. Guardamos
        Srepository.save(statusActivo);

        StatusModel statusInactivo = new StatusModel();
        statusInactivo.setStatusName("Inactive");
        Srepository.save(statusInactivo);

        System.out.println("Estados cargados");
    }

    private void DepData() {
        DepartmentsModel deptoRH = new DepartmentsModel();
// 2. Setear únicamente la data útil del negocio
        deptoRH.setName("Recursos Humanos");
        deptoRH.setStatus(Active); // Asigna el valor o enum que corresponda a "Active"
        deptoRH.setName("Sistemas");
        deptoRH.setStatus(Active);
        deptoRH.setName("Urgencias");
        deptoRH.setStatus(Active);
        deptoRH.setName("Quirófanos");
        deptoRH.setStatus(Active);
        deptoRH.setName("Morgue");
        deptoRH.setStatus(Active);
// 3. Guardar
        Drepository.save(deptoRH);
        System.out.println("Departamentos cargados exitosamente.");
    }

    private void PosData() {

        PositionsModel Posis = new PositionsModel();
        Posis.setName("Gerente");
        Posis.setDescription("Encargado de funciones internas");
        Posis.setStatus(Active);
        Posis.setName("Secretaria");
        Posis.setDescription("Gestor de juntas");
        Posis.setStatus(Active);
        Prepository.save(Posis);
        System.out.println("Posiciones cargados exitosamente.");
    }

    @Override
    public void run(String... args) throws Exception {
        if (Srepository.count() == 0) {
            StaData();
        }
        Active = Srepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Error: Estado 'Activo' no encontrado"));

        if (Drepository.count() == 0) {
            DepData();
        }
        if (Prepository.count() == 0) {
            PosData();
        }
    }
}
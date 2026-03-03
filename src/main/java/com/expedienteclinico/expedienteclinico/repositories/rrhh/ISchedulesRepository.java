package com.expedienteclinico.expedienteclinico.repositories.rrhh;
import com.expedienteclinico.expedienteclinico.models.rrhh.SchedulesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISchedulesRepository extends JpaRepository<SchedulesModel, Integer> {
}

package com.expedienteclinico.expedienteclinico.repositories.rrhh;
import com.expedienteclinico.expedienteclinico.models.rrhh.DepartmentsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartmentsRepository extends JpaRepository<DepartmentsModel, Integer> {
}

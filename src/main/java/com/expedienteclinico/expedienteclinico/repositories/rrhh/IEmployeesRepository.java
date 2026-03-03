package com.expedienteclinico.expedienteclinico.repositories.rrhh;
import com.expedienteclinico.expedienteclinico.models.rrhh.EmployeesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeesRepository extends JpaRepository<EmployeesModel, Integer> {
}

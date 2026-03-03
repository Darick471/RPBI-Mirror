package com.expedienteclinico.expedienteclinico.repositories.rrhh;
import com.expedienteclinico.expedienteclinico.models.rrhh.ClinicDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClinicDetailsRepository extends JpaRepository<ClinicDetailsModel, Integer> {
}

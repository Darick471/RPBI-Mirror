package com.expedienteclinico.expedienteclinico.repositories.rrhh;
import com.expedienteclinico.expedienteclinico.models.rrhh.ContractsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContractsRepository extends JpaRepository<ContractsModel, Integer> {
}

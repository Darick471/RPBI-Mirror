package com.expedienteclinico.expedienteclinico.repositories.rrhh;
import com.expedienteclinico.expedienteclinico.models.rrhh.PositionsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPositionsRepository extends JpaRepository<PositionsModel, Integer> {
}

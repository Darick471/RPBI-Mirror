package com.expedienteclinico.expedienteclinico.repositories.rpbi;

import com.expedienteclinico.expedienteclinico.models.rpbi.RpbiClasificationModel;
import com.expedienteclinico.expedienteclinico.models.rpbi.RpbiPhysicalStateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRpbiPhysicalStateRepository extends JpaRepository<RpbiPhysicalStateModel, Long>{

    // Para poder "Traducir" de UUID a ID en las operaciones internas
    Optional<RpbiPhysicalStateModel> findByUuid(String uuid);

}

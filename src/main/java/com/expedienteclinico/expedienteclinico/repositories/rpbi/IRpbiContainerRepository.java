package com.expedienteclinico.expedienteclinico.repositories.rpbi;

import com.expedienteclinico.expedienteclinico.models.rpbi.RpbiClasificationModel;
import com.expedienteclinico.expedienteclinico.models.rpbi.RpbiContainerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRpbiContainerRepository extends JpaRepository<RpbiContainerModel, Long>{

    // Para poder "Traducir" de UUID a ID en las operaciones internas
    Optional<RpbiContainerModel> findByUuid(String uuid);

}

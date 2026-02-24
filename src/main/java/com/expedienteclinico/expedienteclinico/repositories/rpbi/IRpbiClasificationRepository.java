package com.expedienteclinico.expedienteclinico.repositories.rpbi;

import com.expedienteclinico.expedienteclinico.models.rpbi.RpbiClasificationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRpbiClasificationRepository extends JpaRepository<RpbiClasificationModel, Long>{

    // Para poder "Traducir" de UUID a ID en las operaciones internas
    Optional<RpbiClasificationModel> findByUuid(String uuid);

}

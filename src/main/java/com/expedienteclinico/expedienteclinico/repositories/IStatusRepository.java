package com.expedienteclinico.expedienteclinico.repositories;

import com.expedienteclinico.expedienteclinico.models.StatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusRepository extends JpaRepository<StatusModel, Long> {
}

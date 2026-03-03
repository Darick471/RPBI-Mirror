package com.expedienteclinico.expedienteclinico.repositories.Patients;

import com.expedienteclinico.expedienteclinico.models.patients.TriageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITriageRepository  extends JpaRepository<TriageModel, UUID> {

}
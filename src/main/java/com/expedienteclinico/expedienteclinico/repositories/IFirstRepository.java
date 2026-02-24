package com.expedienteclinico.expedienteclinico.repositories;

import com.expedienteclinico.expedienteclinico.models.FirstModel;
import org.springframework.data.jpa.repository.JpaRepository;
@Deprecated
public interface IFirstRepository extends JpaRepository< FirstModel , Long > {}

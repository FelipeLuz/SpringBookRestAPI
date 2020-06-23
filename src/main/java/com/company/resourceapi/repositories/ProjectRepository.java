package com.company.resourceapi.repositories;

import com.company.resourceapi.entities.Project;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

    ///**
     //* find project by SDLC-ID and project-ID
     //* @param sdlcSystemId
     //* @param projectId
     //* @return
     //*/
    //@Query("SELECT * FROM project WHERE project.id = :projectId AND project.sdlc_system_id = :sdlcSystemId")
    //Optional<Project> findBySdlcSystemIdAndId(@Param("sdlcSystemId") long sdlcSystemId, @Param("projectId") long projectId);
    Optional<Project> findBySdlcSystemIdAndId(long sdlcSystemId, long projectId);
}

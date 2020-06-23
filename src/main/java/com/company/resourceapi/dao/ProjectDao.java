package com.company.resourceapi.dao;

import com.company.resourceapi.entities.Project;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDao {

    public Project getProject(long id);

    public Project save(Project newProject);
}

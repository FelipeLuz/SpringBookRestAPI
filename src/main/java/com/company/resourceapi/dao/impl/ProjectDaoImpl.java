package com.company.resourceapi.dao.impl;

import com.company.resourceapi.dao.ProjectDao;
import com.company.resourceapi.entities.Project;
import com.company.resourceapi.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project getProject(long id) {
        return this.projectRepository.findById(id).orElse(null);
    }

    @Override
    public Project save(Project newProject) {
        return this.projectRepository.save(newProject);
    }
}

